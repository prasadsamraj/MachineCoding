package strategies.botStrategies;

import models.Board;
import models.Move;

public interface BotDifficultyStrategy {
    public Move makeMove(Board board);
}
