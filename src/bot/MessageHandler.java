package bot;

import interfaces.IGameMode;

public class MessageHandler {
    private IGameMode game;

    public String GetStartMessage(){
        return "Привет! Я бот, с которым можно поиграть. Начать игру? (y/n)";
    }

    public String HandleMessage(String message){
        message = message.toLowerCase().replaceAll(System.getProperty("line.separator"), " ").trim();
        
        if (game != null){
            switch (message){
                case "s":
                    return "Игра началась!\n" +
                            "Если вопрос слишком сложный, отправь \"n\" и я его пропущу.\n" +
                            "Когда игра надоест, отправь \"f\" и она закончится.\n\n" + game.GetQuestion();
                case "f":
                    game = null;
                    return "Игра окончена. Хочешь поиграть ещё? (y/n)";
                case "n":
                    return game.Skip();
                default:
                    return game.CheckUserAnswer(message);
            }
        }

        switch (message){
            case "n":
                return "Ок, если передумаешь напиши \"y\".";

            case "y":
                game = new SimplestGameMod();
                return this.HandleMessage("s");

            default:
                return "Я таких слов не знаю!";
        }
    }
}
