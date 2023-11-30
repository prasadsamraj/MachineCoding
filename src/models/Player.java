package models;

import java.util.Scanner;

public class Player {
    private String name;
    private PlayerType playerType;
    private char Symbol;

    public Player(String name, PlayerType playerType, char symbol) {
        this.name = name;
        this.playerType = playerType;
        Symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public char getSymbol() {
        return Symbol;
    }

    public void setSymbol(char symbol) {
        Symbol = symbol;
    }

    public Move makeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the row number");
        int row = sc.nextInt();
        System.out.println("Please enter the col number");
        int col = sc.nextInt();
        return new Move(this, new Cell(row, col));
    }
}
