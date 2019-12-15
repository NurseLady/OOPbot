package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.dataClasses.UserInfo;

public class SkipQuestion extends Command {
    public SkipQuestion(String name) { super(name, "Пропустить вопрос"); }

    @Override
    public void exec(String message, UserInfo userInfo) {

        var question = GameManager.getGame(userInfo.gameKey).Skip();
        userInfo.serviceCommandsInformation = String.valueOf(question.correctAnswerIndex);
        manager.sendMessage(question.toString(), userInfo.ID);
    }
}
