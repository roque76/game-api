package com.game.gameapi.service;

import com.game.gameapi.exceptions.TangoException;
import com.game.gameapi.model.Question;
import com.game.gameapi.model.QuestionList;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Data
public class QuestionListService {
    private QuestionList questions;

    public QuestionListService(){
        questions = new QuestionList();
        // falta leer de archivo preguntas de un txt o de una db
        questions.addQuestion(new Question("What is the capital of France?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 0, "q1"));
        questions.addQuestion(new Question("What is the capital of England?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 1, "q2"));
        questions.addQuestion(new Question("What is the capital of Germany?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 2, "q3"));
        questions.addQuestion(new Question("What is the capital of Spain?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 3, "q4"));
    }

    public String addNewQuestion(Question newQuestion){
        questions.addQuestion(newQuestion);
        return "Adicionado";
    }
    public String deleteQuestionById(String questionId)throws TangoException{
        boolean output = questions.deleteQuestion(questionId);
        if (output){
            return "Eliminado";
        }
        else{
            throw new TangoException("No se encontro la id");
        }
    }
    public String updateQuestion(String questionId,Question updatedQuestion) throws TangoException{
        try {
            questions.updateQuestion(questionId,updatedQuestion);
            return "Pregunta actualizada";
        } catch (TangoException e) {
            throw new TangoException(e.getMessage());
        }
    }
    public List<Question> getAll(){
        return questions.getAll();
    }
    public Question getQuestionById(String questionId) throws TangoException{
        try {
            return questions.getQuestionById(questionId);
        } catch (TangoException e) {
            throw new TangoException(e.getMessage());
        }
    }
}
