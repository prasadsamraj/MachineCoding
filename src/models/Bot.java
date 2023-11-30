package models;

import strategies.botStrategies.BotDifficultyStrategy;
import strategies.botStrategies.BotDifficultyStrategyFactory;

public class Bot extends Player{
    private DifficultyLevel difficultyLevel;
    private BotDifficultyStrategy botDifficultyStrategy;

    public Bot(String name, char symbol, DifficultyLevel difficultyLevel) {
        super(name, PlayerType.BOT, symbol);
        this.difficultyLevel = difficultyLevel;
        this.botDifficultyStrategy = BotDifficultyStrategyFactory.getBotDifficultyStrategyFactory(difficultyLevel);
    }

    public BotDifficultyStrategy getBotDifficultyStrategy() {
        return botDifficultyStrategy;
    }

    public void setBotDifficultyStrategy(BotDifficultyStrategy botDifficultyStrategy) {
        this.botDifficultyStrategy = botDifficultyStrategy;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public Move makeMove(Board board){
        return botDifficultyStrategy.makeMove(board);
    }
}
