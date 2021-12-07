package com.prac3;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards;
    String name;
    public Player(String name){
        this.name = name;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public String getName(){
        return name;
    }
}
