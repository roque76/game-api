package com.game.gameapi.controller;

import com.game.gameapi.controller.dto.ResponseDTO;
import com.game.gameapi.exceptions.TangoException;
import com.game.gameapi.model.Kid;
import com.game.gameapi.service.ListDECircularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "listdecircular")
public class ListDECircularController {
    @Autowired
    private ListDECircularService listDECircularService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listDECircularService.getAll(),null),HttpStatus.OK);
        } catch (TangoException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @PostMapping(path = "/insertinpos/{pos}")
    public ResponseEntity<ResponseDTO> insertInPos(@PathVariable int pos, @RequestBody Kid kid){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listDECircularService.insertInPos(pos,kid),null),HttpStatus.OK);
        } catch (TangoException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/deleteinpos/{pos}")
    public ResponseEntity<ResponseDTO> deleteInPos(@PathVariable int pos){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listDECircularService.deleteInPos(pos),null),HttpStatus.OK);
        } catch (TangoException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @GetMapping(path = "/movekid/{pos}/{kidid}")
    public ResponseEntity<ResponseDTO> moveKid(@PathVariable int pos, @PathVariable String kidid){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listDECircularService.moveKid(pos,kidid),null),HttpStatus.OK);
        } catch (TangoException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
}
