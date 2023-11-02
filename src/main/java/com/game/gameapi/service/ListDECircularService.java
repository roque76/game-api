package com.game.gameapi.service;

import com.game.gameapi.exceptions.TangoException;
import com.game.gameapi.model.Kid;
import com.game.gameapi.model.ListDECircular;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListDECircularService {
    private ListDECircular kids;
    public ListDECircularService(){
        kids = new ListDECircular();
        kids.addToEnd(new Kid("John Jaime","01"));
        kids.addToEnd(new Kid("Jhair Torres","02"));
        kids.addToEnd(new Kid("Sergio Sneyder","03"));
        kids.addToEnd(new Kid("Valeria Osorio","04"));
        kids.addToStart(new Kid("Rugeles","05"));
    }

    public List<Kid> getAll() throws TangoException{
        try {
            return kids.getAll();
        } catch (TangoException e) {
            throw new TangoException(e.getMessage());
        }
    }
    public String deleteInPos(int pos) throws TangoException{
        try {
            kids.deleteByPos(pos);
            return "Eliminado";
        } catch (TangoException e) {
            throw new TangoException(e.getMessage());
        }
    }
    public String insertInPos(int pos, Kid kid){
        kids.insertInPos(pos,kid);
        return "Adicionado";
    }
}
