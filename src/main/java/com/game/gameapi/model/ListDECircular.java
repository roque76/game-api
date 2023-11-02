package com.game.gameapi.model;

import com.game.gameapi.exceptions.TangoException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDECircular {
    private NodeDECircular head;
    private int size;

    public void addToStart(Kid kid){
        NodeDECircular newNode = new NodeDECircular(kid);
        if (this.head ==null){
            this.head=newNode;
            this.head.setPrevious(this.head);
            this.head.setNext(this.head);
        }
        else{
            NodeDECircular lastNode = this.head.getPrevious();
            this.head.setPrevious(newNode);
            lastNode.setNext(newNode);
            newNode.setPrevious(lastNode);
            newNode.setNext(this.head);
            this.head=newNode;
        }
        this.size++;
        System.out.println("New size value: "+this.size);

    }

    public void addToEnd(Kid kid){
        NodeDECircular newNode = new NodeDECircular(kid);
        if(this.head==null){
            this.head =newNode;
            this.head.setNext(this.head);
            this.head.setPrevious(this.head);
        } else {
              this.head.getPrevious().setNext(newNode);
              newNode.setPrevious(this.head.getPrevious());
              this.head.setPrevious(newNode);
              newNode.setNext(this.head);

        }
        this.size++;
        System.out.println("New size value: "+this.size);
    }

    public List<Kid> getAll() throws TangoException {
        if(this.head==null){
            throw new TangoException("Lista vacia");
        }
        else{
            List<Kid> kids = new ArrayList<>();
            NodeDECircular temp = this.head;
           do{
               kids.add(temp.getData());
               temp = temp.getNext();
           }
           while (temp!=this.head);
           return kids;
        }
    }
    public void insertInPos (int pos, Kid kid) {
        if (pos == 1) {
            this.addToStart(kid);

        } else if (pos > this.size) {
            this.addToEnd(kid);

        } else if (pos <= this.size) {
            NodeDECircular temp = this.head;
            int posAct = 1;
            while (posAct < pos - 1) {
                temp = temp.getNext();
                posAct++;
            }
            NodeDECircular newNode = new NodeDECircular(kid);
            temp.getNext().setPrevious(newNode);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            this.size++;
        }

    }

    public void deleteByPos (int pos) throws TangoException {
        if (pos <= 0 || pos > this.size) {
            throw new TangoException("Fuera de rango");
        }
        if(pos == 1){
            NodeDECircular lastNode = this.head.getPrevious();
            lastNode.setNext(this.head.getNext());
            this.head.setPrevious(lastNode);
            this.head = this.head.getNext();
        }
        else{
            NodeDECircular temp = this.head;
            int cont = 1;

            while (cont < pos -1){
                temp = temp.getNext();
                cont++;
            }
            temp.getNext().getNext().setPrevious(temp);
            temp.setNext(temp.getNext().getNext());

        }
        this.size--;
        System.out.println("New size value: "+this.size);
    }

}
