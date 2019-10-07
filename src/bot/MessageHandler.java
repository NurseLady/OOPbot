package bot;

import interfaces.GameMode;

import static bot.StringConstants.*;


public class MessageHandler {
    private GameMode game;

    public String handleMessage(String message){
        message = message.toLowerCase().replaceAll(System.getProperty("line.separator"), " ").trim();
        String result;
        
        if (game != null){
            switch (message){
                case "s":
                    result = gameStartMessage + game.getQuestion();
                    break;
                case "f":
                    game = null;
                    result = gameEndMessage;
                    break;
                case "n":
                    result = game.Skip();
                    break;
                default:
                    result = game.checkUserAnswer(message);
                    break;
            }
        } else {
            switch (message) {
                case "n":
                    result = noGameMessage;
                    break;
                case "y":
                    game = new SimplestGameMod(new SimplestQuestionsGenerator());
                    result = this.handleMessage("s");
                    break;
                default:
                    result = incorrectInputMessage;
                    break;
            }
        }
        return result;
    }

    public GameMode getGame (){
        return game;
    }
}
