package com.game.gameapi.model;

import lombok.Data;
import org.w3c.dom.Node;

@Data
public class NodeDECircular {
    private Kid data;
    private NodeDECircular next;
    private NodeDECircular previous;

    public NodeDECircular(Kid data) {
        this.data = data;
    }
}
