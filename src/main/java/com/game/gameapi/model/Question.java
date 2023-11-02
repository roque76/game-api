package com.game.gameapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private String question;
    private List<String> options;
    private byte correctPos;
    private String id;
}
