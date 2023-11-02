package com.game.gameapi.model;

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
}
