import controllers.GameController;
import exceptions.botCountMoreThanOneException;
import exceptions.playerCountIncorrectException;
import exceptions.sizeIncorrectException;
import exceptions.winningStrategyMandatoryException;
import models.*;
import strategies.winningStrategies.ColWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws winningStrategyMandatoryException, sizeIncorrectException, playerCountIncorrectException, botCountMoreThanOneException {
        GameController gameController = new GameController();
        int size = 3;
        List<Player> players = new ArrayList<>();
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        players.add(new Player("Prasad", PlayerType.HUMAN, 'X'));
        players.add(new Bot("Computer", 'O', DifficultyLevel.EASY));
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());
        Game game = gameController.startGame(size, players, winningStrategies);
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
            gameController.displayBoard(game);
            System.out.println("Do you want to undo? Y/N");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("Y")){
                System.out.println("Undoing the last move");
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }
        gameController.displayBoard(game);
        if(gameController.getGameStatus(game).equals(GameStatus.WIN)){
            System.out.println(gameController.getWinner(game)+" is the Winner!");
        }else{
            System.out.println("Game ended in DRAW");
        }
    }
}