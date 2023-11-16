package com.game.gameapi.service;

import com.game.gameapi.exceptions.TangoException;
import com.game.gameapi.model.City;
import com.game.gameapi.model.Kid;
import com.game.gameapi.model.ListDECircular;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListDECircularService {
    private ListDECircular kids;

    @Autowired
    private CityService cityService;
    public ListDECircularService(){
        kids = new ListDECircular();
        kids.addToEnd(new Kid("John Jaime","01",new City("Manizales","01")));
        kids.addToEnd(new Kid("Jhair Torres","02",new City("Mocoa","04")));
        kids.addToEnd(new Kid("Sergio Sneyder","03",new City("Pitalito","03")));
        kids.addToEnd(new Kid("Valeria Osorio","04",new City("Pereira","02")));
        kids.addToStart(new Kid("Rugeles","05",new City("Manizales","01")));
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
    public String insertInPos(int pos, Kid kid) throws TangoException{

        try {
            cityService.getCityById(kid.getKidCity().getCityId());
            kids.insertInPos(pos,kid);
            return "Adicionado";
        } catch (TangoException e) {
            throw new TangoException("La ciudad ingresada no se encuentra en la lista");
        }


    }
    public String addToEnd(Kid kid)throws TangoException {
        try {
            cityService.getCityById(kid.getKidCity().getCityId());
            kids.addToEnd(kid);
            return "Adicionado";
        } catch (TangoException e) {
            throw new TangoException("la ciudad ingresada no se encuentra en la lista global");
        }
    }

    public String addToStart(Kid kid) throws TangoException{
        try {
            cityService.getCityById(kid.getKidCity().getCityId());
            kids.addToStart(kid);
            return "Adicionado";
        } catch (TangoException e) {
            throw new TangoException("La ciudad ingresada no esta en lista global");
        }
    }

    public String moveKid(int pos, String kidId) throws TangoException{
        try {
            kids.moveKid(pos,kidId);
            return "Niño movido";
        } catch (TangoException e) {
            throw new TangoException(e.getMessage());
        }
    }
}
