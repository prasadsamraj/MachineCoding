package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> board;
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public List<List<Cell>> getBoard() {
        return board;
    }
    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
    public Board(int size) {
        this.size = size;
        board = new ArrayList<>();
        for(int i=0; i<size; i++){
            List<Cell> cells = new ArrayList<>();
            for(int j=0; j<size; j++){
                cells.add(new Cell(i,j));
            }
            board.add(cells);
        }
    }

    public boolean validate(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        return row>=0 && row<size && col>=0 && col<size && board.get(row).get(col).getCellStatus().equals(CellStatus.EMPTY);
    }

    public void displayBoard() {
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                Cell cell = board.get(i).get(j);
                if(cell.getCellStatus().equals(CellStatus.EMPTY))
                    System.out.print("| |");
                else
                    System.out.print("|"+cell.getPlayer().getSymbol()+"|");
            }
            System.out.println();
        }
    }
}
