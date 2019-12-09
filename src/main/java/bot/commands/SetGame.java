package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.GameManager;
import bot.dataClasses.UserInfo;

import static bot.StringConstants.changeGameModMessage;
import static bot.StringConstants.incorrectInputMessage;

public class SetGame extends Command {
    public SetGame(String name) { super(name); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        try {
            int i = Integer.parseInt(message);
            if (GameManager.getGames().containsKey(i)){
                userInfo.state = "menu";
                userInfo.gameKey = i;
                return changeGameModMessage + userInfo.gameKey + "\n\n" +
                        CommandManager.getCommandList("menu");
            }

        } catch (Exception ignored){ }

        return incorrectInputMessage;
    }
}
