package bot;

import com.google.common.annotations.VisibleForTesting;
import interfaces.GameMode;

import static bot.StringConstants.*;


public class MessageHandler {
    private GameMode game;

    public String handleMessage(String message){
        message = message.toLowerCase().replaceAll(System.getProperty("line.separator"), " ").trim();
        String result;
        
        if (game != null){
            if ("s".equals(message)) {
                result = gameStartMessage + game.getQuestion();
            } else if ("f".equals(message)) {
                game = null;
                result = gameEndMessage;
            } else if ("n".equals(message)) {
                result = game.Skip();
            } else {
                result = game.checkUserAnswer(message);
            }
        } else {
            if ("n".equals(message)) {
                result = noGameMessage;
            } else if ("y".equals(message)) {
                game = new SimplestGameMod(new SimplestQuestionsGenerator());
                result = this.handleMessage("s");
            } else {
                result = incorrectInputMessage;
            }
        }
        return result;
    }

    @VisibleForTesting
    public GameMode getGame (){
        return game;
    }
}
