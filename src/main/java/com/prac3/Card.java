package com.prac3;

public class Card {
    private char suit;
    private Integer value;
    private String valueChar;
    public Card(String value){
        this.suit = value.charAt(value.length() - 1);
        String temp = value.substring(0, value.length()-1);
        switch(temp)
        {
            case("A"):
                temp = "14";
                this.valueChar = "A";
                break;
            case("K"):
                temp = "13";
                this.valueChar = "K";
                break;
            case("Q"):
                temp = "12";
                this.valueChar = "Q";
                break;
            case("V"):
                temp = "11";
                this.valueChar = "V";
                break;
            case("10"):
                this.valueChar = "10";
                break;
            default:
                this.valueChar = temp;
        }

        this.value = Integer.parseInt(temp);
    }
    @Override
    public String toString(){
        return this.valueChar + this.suit;
    }

    public char getSuit() {
        return suit;
    }

    public Integer getValue() {
        return value;
    }
}
