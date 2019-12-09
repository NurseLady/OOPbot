package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.StringConstants;
import bot.dataClasses.UserInfo;

public class CheckAnswer extends Command {
    public CheckAnswer(String name) { super(name, ""); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        if (GameManager.getGame(userInfo.gameKey).checkUserAnswer(message, Integer.parseInt(userInfo.serviceCommandsInformation))){
            userInfo.score += 2;
            return StringConstants.correctAnswerMessage + new SkipQuestion("").exec("", userInfo);
        }
        userInfo.score--;
        return StringConstants.incorrectAnswerMessage;
    }
}