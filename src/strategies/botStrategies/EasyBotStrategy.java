package strategies.botStrategies;

import models.Board;
import models.Cell;
import models.CellStatus;
import models.Move;

public class EasyBotStrategy implements BotDifficultyStrategy {
    @Override
    public Move makeMove(Board board) {
        int size = board.getSize();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(board.getBoard().get(i).get(j).getCellStatus().equals(CellStatus.EMPTY))
                    return new Move(null, new Cell(i,j));
            }
        }
        return null;
    }
}
