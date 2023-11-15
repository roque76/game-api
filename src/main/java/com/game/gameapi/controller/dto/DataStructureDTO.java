package com.game.gameapi.controller.dto;

import com.game.gameapi.model.Kid;
import com.game.gameapi.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataStructureDTO {
    private Kid kidData;
    private Question questionData;
}
