package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {
    public boolean checkWinner(Move move, int size);

    void undo(Move move, int size);
}
