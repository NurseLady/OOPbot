package bot.commands;

import bot.Command;
import bot.CommandManager;
import bot.GameManager;
import bot.UserManager;
import bot.dataClasses.UserInfo;

import java.util.HashMap;

public class SelectGame extends Command {
    public SelectGame(String name) { super(name, "Выбрать режим игры"); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        userInfo.state = "select";
        var games = GameManager.getGames();

        var keyboardButtons = new HashMap<String, String>();
        for (var i : games.keySet()){
            keyboardButtons.put(String.valueOf(i), i + ". " + games.get(i).getName());
        }

        manager.sendMessageWithInlineKeyboard(keyboardButtons, "Режим игры:", userInfo.ID);
        UserManager.updateUserSave(userInfo);
    }
}
