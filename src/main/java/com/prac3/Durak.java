package com.prac3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.shuffle;

public class Durak {
    private ArrayList<Card> deck;
    private char trump;
    private int table = 0;
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

    public boolean draw(Player player){
        if(player.getCards().size() == 6 || this.deck.size() == 0)
            return false;
        else
        {
            ArrayList<Card> temp = player.getCards();
            while(temp.size()<6 && this.deck.size() > 0)
                temp.add(this.deck.remove(this.deck.size()-1));
            return true;
        }
    }

    public void replenishDeck(){
        this.deck = generateDeck();
    }

    public boolean throwAnother(Card card, List<Integer> canAdd){
        if(canAdd.contains(card.getValue())) {
            table++;
            return true;
        }
        else return false;
    }

    public void printUI(Player player1){
        System.out.println(player1.getName() + ", your turn!");
        System.out.print("Your deck: ");
        for(Card card : player1.getCards()){
            System.out.print(card + " ");
        }
        System.out.println(String.format("Choose your card (input the number of the card from left to right (%d - %d))",
                1, player1.getCards().size()));
    }
    public void printCurrentTable(List<Card> current){
        System.out.println("Current cards on the table: ");
        for (Card card : current){
            System.out.print(card + "");
        }
    }
}
