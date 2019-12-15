package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.GameManager;
import bot.dataClasses.UserInfo;

public class SelectGame extends Command {
    public SelectGame(String name) { super(name, "Выбрать режим игры"); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        userInfo.state = "select";
        manager.sendMessageWithKeyboard(userInfo.state,
                CommandManager.getCommandList("select") + GameManager.getGamesList(), userInfo.ID);
    }
}
