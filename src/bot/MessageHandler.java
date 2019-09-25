package bot;

import interfaces.GameMode;

class MessageHandler {
    private GameMode game;
    String startMessage = "Привет! Я бот, с которым можно поиграть. Начать игру? (y/n)";
    private String gameStartMessage = "Игра началась!\n" +
            "Если вопрос слишком сложный, отправь \"n\" и я его пропущу.\n" +
            "Когда игра надоест, отправь \"f\" и она закончится.\n\n";
    private String gameEndMessage = "Игра окончена. Хочешь поиграть ещё? (y/n)";
    private String noGameMessage = "Отправь \"y\" если передумаешь.";
    private String incorrectInputMessage = "Я таких слов не знаю!";

    String handleMessage(String message){
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
                    game = new SimplestGameMod();
                    result = this.handleMessage("s");
                    break;
                default:
                    result = incorrectInputMessage;
                    break;
            }
        }
        return result;
    }
}
