package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.StringConstants;
import bot.dataClasses.UserInfo;

public class FinishGame extends Command {
    public FinishGame(String name) { super(name); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        userInfo.state = "menu";
        return StringConstants.gameEndMessage + CommandManager.getCommandList("menu");
    }
}