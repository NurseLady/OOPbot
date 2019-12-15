package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.StringConstants;
import bot.dataClasses.UserInfo;

public class CheckAnswer extends Command {
    public CheckAnswer(String name) { super(name, ""); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        if (GameManager.getGame(userInfo.gameKey).checkUserAnswer(message, Integer.parseInt(userInfo.serviceCommandsInformation))){
            userInfo.score += 2;
            manager.sendMessage(StringConstants.correctAnswerMessage, userInfo.ID);
            new SkipQuestion("").exec("", userInfo);
            return;
        }
        userInfo.score--;
        manager.sendMessage(StringConstants.incorrectAnswerMessage, userInfo.ID);
    }
}