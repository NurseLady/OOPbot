package bot.commands;

import bot.Command;
import bot.StringConstants;
import bot.dataClasses.UserInfo;

public class Unknown extends Command {
    public Unknown(String name) { super(name); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        return StringConstants.incorrectInputMessage;
    }
}
