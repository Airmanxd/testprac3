package com.prac3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Player1 please enter your name: ");
        Player player1 = new Player(in.nextLine());
        System.out.print("Player2 please enter your name: ");
        Player player2 = new Player(in.nextLine());
        Durak durak = new Durak();
        Player winner = durak.game(player1, player2);
        System.out.println("Congratulations, " + winner.getName()+ "!");
    }
}
