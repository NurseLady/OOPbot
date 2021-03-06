package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.StringConstants;
import bot.UserManager;
import bot.dataClasses.UserInfo;

import java.io.File;

public class CheckAnswer extends Command {
    public CheckAnswer(String name) { super(name, ""); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        if (GameManager.getGame(userInfo.gameKey).checkUserAnswer(message, Integer.parseInt(userInfo.rightAnswer))){
            userInfo.score += 2;
            manager.sendPhoto(new File("photos" + StringConstants.separator + "truth.png"), userInfo.ID);
            manager.sendMessage(StringConstants.correctAnswerMessage, userInfo.ID);
            new SkipQuestion("").exec("", userInfo);
            return;
        }
        userInfo.score--;
        manager.sendPhoto(new File("photos" + StringConstants.separator + "false.jpg"), userInfo.ID);
        new SkipQuestion("").exec("", userInfo);
        UserManager.updateUserSave(userInfo);
    }
}