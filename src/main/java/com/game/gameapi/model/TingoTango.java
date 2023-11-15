package com.game.gameapi.model;

import com.game.gameapi.controller.dto.DataStructureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TingoTango {
    private ListDECircular participants;
    private List<Question> questions;
    private boolean gameState;
    private boolean answerState;
    private Kid awaitingKid;
    private DataStructureDTO awaitingQuestion;
}
