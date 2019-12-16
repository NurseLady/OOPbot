package bot.commands;

import bot.Command;
import bot.dataClasses.UserInfo;

public class CreateNewGame extends Command {
    public CreateNewGame(String name) { super(name, "Создать новый режим [Disabled]"); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        manager.sendMessage("Пока в разработке :(", userInfo.ID);
    }
}
