package models;

import exceptions.botCountMoreThanOneException;
import exceptions.playerCountIncorrectException;
import exceptions.sizeIncorrectException;
import exceptions.winningStrategyMandatoryException;
import strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<WinningStrategy> winningStrategies;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int size;

    private Game(GameBuilder gameBuilder) {
        this.players = gameBuilder.players;
        this.size = gameBuilder.size;
        this.board = new Board(gameBuilder.size);
        this.winningStrategies = gameBuilder.winningStrategies;
        this.winner = null;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
    }
    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void makeMove() {
        Player player = players.get(nextPlayerIndex);
        System.out.println("It's "+player.getName()+"'s turn to make the move");
        Move move = player.makeMove(this.board);
        if(!board.validate(move)){
            System.out.println("Player made an invalid move...");
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellStatus(CellStatus.FILLED);
        cell.setPlayer(player);
        Move finalMove = new Move(player, cell);
        moves.add(finalMove);
        if(moves.size()==size*size) setGameStatus(GameStatus.DRAW);
        checkWinner(finalMove);
        nextPlayerIndex = (nextPlayerIndex+1)%players.size();
    }

    private void checkWinner(Move move) {
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(move, size)){
                setWinner(move.getPlayer());
                setGameStatus(GameStatus.WIN);
            }
        }
    }

    public void displayBoard() {
        board.displayBoard();
    }

    public void undo() {
        if(moves.isEmpty()){
            System.out.println("No moves to undo.");
            return;
        }
        Move move = moves.remove(moves.size()-1);
        for(WinningStrategy winningStrategy:winningStrategies){
            winningStrategy.undo(move, size);
        }
        nextPlayerIndex = (nextPlayerIndex-1+players.size())%players.size();
        move.getCell().setCellStatus(CellStatus.EMPTY);
        move.getCell().setPlayer(null);
    }

    public static class GameBuilder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int size;

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public GameBuilder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public int getSize() {
            return size;
        }

        public GameBuilder setSize(int size) {
            this.size = size;
            return this;
        }
        public void validateWinningStrategies() throws winningStrategyMandatoryException{
            if(winningStrategies.isEmpty()) throw new winningStrategyMandatoryException();
        }
        public void validatePlayerCount() throws playerCountIncorrectException {
            if(players.size()!=size-1) throw new playerCountIncorrectException();
        }
        public void validateBot() throws botCountMoreThanOneException {
            int botCount = 0;
            for(Player player:players){
                if(player.getPlayerType()==PlayerType.BOT) botCount++;
                if(botCount==2) throw new botCountMoreThanOneException();
            }
        }
        public void validateSize() throws sizeIncorrectException {
            if(size<3) throw new sizeIncorrectException();
        }
        public void validate() throws winningStrategyMandatoryException, botCountMoreThanOneException, playerCountIncorrectException, sizeIncorrectException {
            validateSize();
            validatePlayerCount();
            validateBot();
            validateWinningStrategies();
        }
        public Game build() throws winningStrategyMandatoryException, sizeIncorrectException, playerCountIncorrectException, botCountMoreThanOneException {
            validate();
            return new Game(this);
        }
    }
}
