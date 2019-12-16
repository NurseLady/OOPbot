package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.StringConstants;
import bot.UserManager;
import bot.dataClasses.UserInfo;

public class FinishGame extends Command {
    public FinishGame(String name) { super(name, "Закончить игру"); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        userInfo.state = "menu";
        manager.sendMessageWithKeyboard(userInfo.state,
                StringConstants.gameEndMessage, userInfo.ID);
        UserManager.updateUserSave(userInfo);
    }
}
