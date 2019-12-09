package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.GameManager;
import bot.dataClasses.UserInfo;

public class SelectGame extends Command {
    public SelectGame(String name) { super(name); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        userInfo.state = "select";
        return CommandManager.getCommandList("select") + GameManager.getGamesList();
    }
}
