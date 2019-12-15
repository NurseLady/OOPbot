package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.CommandManager;
import bot.UserManager;
import bot.dataClasses.UserInfo;

import static bot.StringConstants.gameStartMessage;

public class Play extends Command {
    public Play(String name) { super(name, "Играть"); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        userInfo.state = "game";
        manager.sendMessageWithKeyboard(userInfo.state, gameStartMessage, userInfo.ID);
        new SendQuestion().exec("", userInfo);
        UserManager.updateUserSave(userInfo);
    }
}
