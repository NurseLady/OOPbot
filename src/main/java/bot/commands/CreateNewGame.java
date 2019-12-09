package bot.commands;

import bot.Command;
import bot.dataClasses.UserInfo;

public class CreateNewGame extends Command {
    public CreateNewGame(String name) { super(name); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        return "Пока в разработке :(";
    }
}
