package com.game.gameapi.service;

import com.game.gameapi.model.Question;
import com.game.gameapi.model.QuestionList;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Data
public class QuestionListService {
    private QuestionList questions;

    public QuestionListService(){
        Question question1 = new Question("What is the capital of France?", Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 0, "q1");
        Question question2 = new Question("What is the capital of England?", Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 1, "q2");
        Question question3 = new Question("What is the capital of Germany?", Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 2, "q3");
        Question question4 = new Question("What is the capital of Spain?", Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 3, "q4");

        questions = new QuestionList();
        // falta leer de archivo preguntas de un txt o de una db
        questions.addQuestion(question1);
        questions.addQuestion(question2);
        questions.addQuestion(question3);
        questions.addQuestion(question4);
    }

    public String addNewQuestion(Question newQuestion){
        questions.addQuestion(newQuestion);
        return "Adicionado";
    }
    public String deleteQuestionById(String questionId){
        boolean output = questions.deleteQuestion(questionId);
        if (output){
            return "Eliminado";
        }
        else{
            return "No se encuentra esa ID, revisar";
        }
    }
}
