package com.game.gameapi.service;

import com.game.gameapi.exceptions.TangoException;
import com.game.gameapi.model.Kid;
import com.game.gameapi.model.Question;
import com.game.gameapi.model.QuestionList;
import com.game.gameapi.model.TingoTango;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                questionService.getAll(),false,false);
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

}
