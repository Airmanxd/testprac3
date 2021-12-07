package com.prac3;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;

public class Durak {
    private ArrayList<Card> deck;
    private char trump;

    public Durak(){
        this.deck = generateDeck();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> generateDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        for (Integer i = 6; i < 11; i++){
            deck.add(new Card(i.toString()+'S'));
            deck.add(new Card(i.toString()+'D'));
            deck.add(new Card(i.toString()+'C'));
            deck.add(new Card(i.toString()+'H'));
        }
        for (Integer i = 11; i < 15; i++){
            String temp = "";
            switch(i){
                case(11):
                    temp = "V";
                    break;
                case(12):
                    temp = "Q";
                    break;
                case(13):
                    temp = "K";
                    break;
                case(14):
                    temp = "A";
                    break;
            }
            deck.add(new Card(temp+'S'));
            deck.add(new Card(temp+'D'));
            deck.add(new Card(temp+'C'));
            deck.add(new Card(temp+'H'));
        }
        return deck;
    }

    public void randomizeDeck(){
        shuffle(this.deck);
        shuffle(this.deck);
    }

    public ArrayList<Card> getInitialSix(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(deck.remove(deck.size()-1));
        cards.add(deck.remove(deck.size()-1));
        cards.add(deck.remove(deck.size()-1));
        cards.add(deck.remove(deck.size()-1));
        cards.add(deck.remove(deck.size()-1));
        cards.add(deck.remove(deck.size()-1));

        return cards;
    }

    public boolean checkPlay(Card cardToPut, Card cardOnTheTable){
        if(((cardToPut.getSuit() == cardOnTheTable.getSuit()) ||
                (cardToPut.getSuit() == this.trump)) &&
                cardToPut.getValue() > cardOnTheTable.getValue())
            return true;
        else
            return false;
    }
    public void setTrump(){
        this.trump = this.deck.get(deck.size()-1).getSuit();
        for (Card card : this.deck){
            if(card.getSuit() == this.trump)
                card.setTrumpSuit();
        }
    }
    public char getTrumpSuit(){
        return this.trump;
    }
}
