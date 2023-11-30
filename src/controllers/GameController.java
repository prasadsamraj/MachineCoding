package controllers;

import exceptions.botCountMoreThanOneException;
import exceptions.playerCountIncorrectException;
import exceptions.sizeIncorrectException;
import exceptions.winningStrategyMandatoryException;
import models.Game;
import models.GameStatus;
import models.Player;
import strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game startGame(int size, List<Player> players, List<WinningStrategy> winningStrategies) throws winningStrategyMandatoryException, sizeIncorrectException, playerCountIncorrectException, botCountMoreThanOneException {
        return Game.getBuilder().setSize(size).setPlayers(players).setWinningStrategies(winningStrategies).build();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public String getWinner(Game game){
        return game.getWinner().getName();
    }
    public void undo(Game game){
        game.undo();
    }
    public void displayBoard(Game game){
        game.displayBoard();
    }
}
