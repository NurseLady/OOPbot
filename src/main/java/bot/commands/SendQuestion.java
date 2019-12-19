package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.UserManager;
import bot.dataClasses.UserInfo;

import java.util.HashMap;

public class SendQuestion extends Command {
    public SendQuestion() { super("SendQuestion", "SendQuestion"); }

    @Override
    public void exec(String message, UserInfo userInfo) {
        var question = GameManager.getGame(userInfo.gameKey).getQuestion();
        userInfo.rightAnswer = String.valueOf(question.correctAnswerIndex);

        var keyboardButtons = new HashMap<String, String>();
        var i = 0;
        for (var ans : question.answers){
            keyboardButtons.put(String.valueOf(++i), ans);
        }

        manager.sendMessageWithInlineKeyboard(keyboardButtons, question.question, userInfo.ID);
        UserManager.updateUserSave(userInfo);
    }
}
