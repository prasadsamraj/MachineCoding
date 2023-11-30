package strategies.winningStrategies;

import models.Move;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{
    HashMap<Character, Integer> diag1 = new HashMap<>();
    HashMap<Character, Integer> diag2 = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, int size) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character symbol = move.getPlayer().getSymbol();
        if(row==col){
            diag1.put(symbol, diag1.getOrDefault(symbol,0)+1);
            if(diag1.get(symbol)==size) return true;
        }
        if(col==size-row-1){
            diag2.put(symbol, diag2.getOrDefault(symbol,0)+1);
            return diag2.get(symbol) == size;
        }
        return false;
    }

    @Override
    public void undo(Move move, int size) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();
        if(row==col){
            diag1.put(symbol, diag1.get(symbol)-1);
        }
        if(col==size-row-1){
            diag2.put(symbol, diag2.get(symbol)-1);
        }
    }
}
