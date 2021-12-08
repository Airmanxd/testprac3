package com.prac3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.max;
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
        if(cardToPut.getValue()>cardOnTheTable.getValue() &&
                (cardToPut.getSuit()==trump ||
                        cardToPut.getSuit() == cardOnTheTable.getSuit()))
            return true;
        else
            return false;
    }
    public void setTrump(){
        this.trump = this.deck.get(0).getSuit();
        for (Card card : this.deck){
            if(card.getSuit() == this.trump)
                card.setTrumpSuit();
        }
    }
    public char getTrumpSuit(){
        return this.trump;
    }

    public boolean draw(Player player){
        if(player.getCards().size() >= 6 || this.deck.size() == 0)
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
        System.out.println("Trump suit: " + this.trump);
        System.out.print("Your deck: ");
        Integer i = 1;
        for(Card card : player1.getCards()){
            System.out.print(i.toString() + ")" + card + " ");
            i++;
        }
        System.out.println(String.format("\nChoose your card (input the number of the card from left to right (%d - %d))",
                1, player1.getCards().size()));
    }
    public void printCurrentTable(List<Card> current){
        System.out.println("Current cards on the table: ");
        for (Card card : current){
            System.out.print(card + " ");
        }
    }
    public Card turnAttack(Player player1){
        this.printUI(player1);

        Scanner in = new Scanner(System.in);
        int ind = in.nextInt() - 1;
        while(ind > player1.getCards().size()-1){
            System.out.println("The card you chose doesn't exist !! Try again!");
            ind = in.nextInt() - 1;
        }

        return player1.getCards().remove(ind);
    }
    public Card defend(Player player2, List<Card> current){
        this.printUI(player2);
        Card attack = current.get(current.size()-1);
        this.printCurrentTable(current);
        System.out.println("\nCard to beat: " + attack);
        System.out.println("(Input 50 to take all)");
        Scanner in = new Scanner(System.in);
        int ind = in.nextInt() - 1;

        while((ind > player2.getCards().size()-1 || !checkPlay(player2.getCards().get(ind), attack)) && ind!=49){
            System.out.println(String.format("Can't use this card! Try again!\nOr take all (enter 50)",
                    1, player2.getCards().size()));
            ind = in.nextInt() - 1;
        }
        if(ind == 49)
            return null;

        return player2.getCards().remove(ind);
    }
    public Card anotherAttack(Player player1, List<Integer>values, List<Card> current){
        this.printUI(player1);
        this.printCurrentTable(current);
        System.out.println("(Input 40 to end turn)");
        Scanner in = new Scanner(System.in);
        int ind = in.nextInt() - 1;

        while((ind > player1.getCards().size()-1 || !throwAnother(player1.getCards().get(ind), values)) && ind!=39){
            System.out.println("Can't use this card! Try again!\nOr end turn (enter 40)");
            ind = in.nextInt() - 1;
        }
        if (ind == 39)
            return null;

        return player1.getCards().remove(ind);
    }

    private void addition(List<Card> pool, List<Integer> values, Card temp){
        pool.add(temp);
        if(temp.getValue() > 100) {
            values.add(temp.getValue() - 100);
            values.add(temp.getValue());
        }
        else{
            values.add(temp.getValue() + 100);
            values.add(temp.getValue());
        }
    }
    public Player game(Player player1, Player player2){
        this.randomizeDeck();
        this.setTrump();
        player1.setCards(this.getInitialSix());
        player2.setCards(this.getInitialSix());
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        List<Card> currentPool = new ArrayList<>();
        List<Integer> canAdd = new ArrayList<>();
        Card temp;
        int i = 0;
        int ind = 0;
        int max_table = 0;
        while(true){
            ind = i%2;
            table = 0;
            Player attacker = players.get(ind);
            Player defender = players.get((ind+1)%2);
            max_table = defender.getCards().size();
            while(true) {
                temp = turnAttack(attacker);
                addition(currentPool, canAdd, temp);
                table++;
                temp = defend(defender, currentPool);
                if (temp != null) {
                    addition(currentPool, canAdd, temp);
                    break;
                }
                defender.getAll(currentPool);
                table = 0;
                if(!draw(attacker) && attacker.getCards().size() == 0)
                    return attacker;

                currentPool.clear();
                canAdd.clear();
            }
            while(table <= max_table){
                temp = anotherAttack(attacker,canAdd, currentPool);
                if(temp == null){
                    i++;
                    break;
                }
                addition(currentPool, canAdd, temp);

                table++;

                temp = defend(defender, currentPool);
                if(temp == null){
                    defender.getAll(currentPool);
                    break;
                }
                addition(currentPool, canAdd, temp);

            }
            currentPool.clear();
            canAdd.clear();
            if(!draw(attacker) && attacker.getCards().size() == 0)
                return attacker;
            if(!draw(defender) && attacker.getCards().size() == 0)
                return defender;
        }
    }
}
