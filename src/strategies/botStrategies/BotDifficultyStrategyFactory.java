package strategies.botStrategies;

import models.DifficultyLevel;

public class BotDifficultyStrategyFactory {
    public static BotDifficultyStrategy getBotDifficultyStrategyFactory(DifficultyLevel difficultyLevel){
        if(difficultyLevel.equals(DifficultyLevel.EASY)){
            return new EasyBotStrategy();
        }
        return null;
    }
}
