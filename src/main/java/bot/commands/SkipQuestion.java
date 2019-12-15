package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.dataClasses.UserInfo;

import java.util.HashMap;

public class SkipQuestion extends Command {
    public SkipQuestion(String name) { super(name, "Пропустить вопрос"); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        new SendQuestion().exec("", userInfo);
    }
}
