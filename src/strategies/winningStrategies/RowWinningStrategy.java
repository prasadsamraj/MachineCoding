package strategies.winningStrategies;

import models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{
    HashMap<Integer, HashMap<Character, Integer>> rowMap = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, int size) {
        int row = move.getCell().getRow();
        Character symbol = move.getPlayer().getSymbol();
        if(!rowMap.containsKey(row)) rowMap.put(row, new HashMap<>());
        HashMap<Character, Integer> temp = rowMap.get(row);
        temp.put(symbol, temp.getOrDefault(symbol,0)+1);
        return temp.get(symbol)==size;
    }

    @Override
    public void undo(Move move, int size) {
        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();
        HashMap<Character, Integer> temp = rowMap.get(row);
        temp.put(symbol,temp.get(symbol)-1);
    }
}
