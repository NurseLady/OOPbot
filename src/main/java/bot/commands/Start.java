package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.StringConstants;
import bot.dataClasses.UserInfo;

public class Start extends Command {
    public Start(String name) { super(name, ""); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        userInfo.state = "menu";
        return StringConstants.startMessage + CommandManager.getCommandList("menu");
    }
}
