package com.game.gameapi.service;

import com.game.gameapi.controller.dto.DataStructureDTO;
import com.game.gameapi.exceptions.TangoException;
import com.game.gameapi.model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Random;

@Data
@Service
public class TingoTangoService {
    // Dependencies
    private final QuestionListService questionService;
    private final ListDECircularService listDEService;
    //Tingo Tango object
    private TingoTango game;
    @Autowired
    public TingoTangoService(QuestionListService questionService,ListDECircularService listDEService){
        this.listDEService = listDEService;
        this.questionService = questionService;

        this.game = new TingoTango(listDEService.getKids(),
                questionService.getAll(),false,false,null,null);
    }

    public String addNewQuestion (Question newQuestion){
        questionService.addNewQuestion(newQuestion);
        return "Pregunta adicionada";
    }

    public String deleteQuestionById(String questionId) throws TangoException{
        if(!game.isGameState()){
            try {
                return questionService.deleteQuestionById(questionId);
            } catch (TangoException e) {
                throw new TangoException(e.getMessage());
            }
        }
        else{
            throw new TangoException("No se puede eliminar una pregunta si el juego esta en curso");
        }
    }

    public String updateQuestionById(String questionId,Question updQuestion)throws TangoException {
        if (!game.isGameState()) {
            try {
                return questionService.updateQuestion(questionId, updQuestion);
            } catch (TangoException e) {
                throw new TangoException(e.getMessage());
            }
        } else {
            throw new TangoException("No se puede actualizar una pregutna con el juego en curso");
        }
    }

    public List<Question> getQuestions(){
        return questionService.getAll();
    }

    public Question getQuestionById(String questionId) throws TangoException{
        try {
            return questionService.getQuestionById(questionId);
        } catch (TangoException e) {
            throw new TangoException(e.getMessage());
        }
    }

    public String addKidToEnd (Kid kid){
       return listDEService.addToEnd(kid);
    }

    public String addToStart (Kid kid){
        return listDEService.addToStart(kid);
    }

    public String deleteKidInPos(int pos)throws TangoException{
        if(!game.isGameState()) {
            try {
                return listDEService.deleteInPos(pos);
            } catch (TangoException e) {
                throw new TangoException(e.getMessage()); 
            }
        }
        else {
            throw new TangoException("No se puede eliminar un participante " +
                    "mientras el juego esta en curso");
        }
    }

    public List<Kid> getAllKids () throws TangoException{
        try {
            return listDEService.getAll();
        } catch (TangoException e) {
            throw new TangoException(e.getMessage());
        }
    }

    public String moveKid (int pos, String kidId) throws TangoException{
      if (game.getAwaitingKid()==null || !kidId.equals(game.getAwaitingKid().getId())) {
          try {
              listDEService.moveKid(pos,kidId);
              return "Niño movido";
          } catch (TangoException e) {
              throw new TangoException(e.getMessage());
          }
      } else {
            throw new TangoException("No se puede mover puesto que no se ha respondido");
        }
    }

    public DataStructureDTO roleGame() throws TangoException{
        if(game.getAwaitingKid()==null) {
            Random rand = new Random();
            int randomPosition = rand.nextInt(2000);
            int actualKidPosition = randomPosition % listDEService.getKids().getSize();
            int actualQuestionPos = randomPosition % questionService.getAll().size();

            NodeDECircular temp;
            if (game.getAwaitingKid() == null) {
                temp = listDEService.getKids().getHead();
            } else {
                temp = new NodeDECircular(game.getAwaitingKid());
            }
            while (actualKidPosition > 0) {
                temp = temp.getNext();
                actualKidPosition--;
            }
            Question question = questionService.getAll().get(actualQuestionPos);
            question.setCorrectPos(null);

            game.setAnswerState(true);
            game.setAwaitingKid(temp.getData());
            game.setGameState(true);
            game.setAwaitingQuestion(new DataStructureDTO(temp.getData(),question));

            return new DataStructureDTO(temp.getData(), question);
        }
        else {
            throw new TangoException("No se puede jugar si todavia no se ha respondido");
        }

    }
    public DataStructureDTO getQuestion (){
        return game.getAwaitingQuestion();
    }

}
