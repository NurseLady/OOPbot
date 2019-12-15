package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.GameManager;
import bot.dataClasses.UserInfo;

import static bot.StringConstants.changeGameModMessage;
import static bot.StringConstants.incorrectInputMessage;

public class SetGame extends Command {
    public SetGame(String name) { super(name, ""); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        try {
            int i = Integer.parseInt(message);
            if (GameManager.getGames().containsKey(i)){
                userInfo.state = "menu";
                userInfo.gameKey = i;
                manager.sendMessage(changeGameModMessage + userInfo.gameKey + "\n\n" +
                        CommandManager.getCommandList("menu"), userInfo.ID);
                manager.sendKeyboard(userInfo.ID);
                return;
            }

        } catch (Exception ignored){ }

        manager.sendMessage(incorrectInputMessage, userInfo.ID);
    }
}
