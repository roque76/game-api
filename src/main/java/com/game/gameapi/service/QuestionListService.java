package com.game.gameapi.service;

import com.game.gameapi.exceptions.TangoException;
import com.game.gameapi.model.Question;
import com.game.gameapi.model.QuestionList;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class QuestionListService {
    private QuestionList questions;

    public QuestionListService() {
        questions = new QuestionList();

        try (BufferedReader reader = new BufferedReader(new FileReader("questions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String questionText = parts[0];
                String[] options = parts[1].split(",");
                Byte correctPos = Byte.parseByte(parts[2]);
                String id = parts[3];

                Question question = new Question(questionText, Arrays.asList(options), correctPos, id);
                questions.addQuestion(question);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
