package bot.commands;

import bot.Command;
import bot.StringConstants;
import bot.dataClasses.UserInfo;

public class Unknown extends Command {
    public Unknown(String name) { super(name, ""); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        manager.sendMessage(StringConstants.incorrectInputMessage, userInfo.ID);
    }
}
