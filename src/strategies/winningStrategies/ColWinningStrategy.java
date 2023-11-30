package strategies.winningStrategies;

import models.Move;

import java.util.HashMap;

public class ColWinningStrategy implements WinningStrategy {
    HashMap<Integer, HashMap<Character, Integer>> colMap = new HashMap<>();
    @Override

    public boolean checkWinner(Move move, int size) {
        int col = move.getCell().getCol();
        Character symbol = move.getPlayer().getSymbol();
        if(!colMap.containsKey(col)) colMap.put(col, new HashMap<>());
        HashMap<Character, Integer> temp = colMap.get(col);
        temp.put(symbol, temp.getOrDefault(symbol,0)+1);
        return temp.get(symbol)==size;
    }

    @Override
    public void undo(Move move, int size) {
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();
        HashMap<Character, Integer> temp = colMap.get(col);
        temp.put(symbol,temp.get(symbol)-1);
    }
}
