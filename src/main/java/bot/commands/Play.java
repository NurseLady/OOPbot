package bot.commands;

import bot.Command;
import bot.GameManager;
import bot.CommandManager;
import bot.dataClasses.UserInfo;

import static bot.StringConstants.gameStartMessage;

public class Play extends Command {
    public Play(String name) { super(name); }

    @Override
    public String exec(String message, UserInfo userInfo) {
        userInfo.state = "game";
        var question = GameManager.getGame(userInfo.gameKey).getQuestion();
        userInfo.serviceCommandsInformation = String.valueOf(question.correctAnswerIndex);
        return gameStartMessage + CommandManager.getCommandList(userInfo.state) + question;
    }
}
