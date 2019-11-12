package bot;

import com.google.common.annotations.VisibleForTesting;
import interfaces.GameMode;
import interfaces.Command;

import java.util.HashMap;

import static bot.StringConstants.*;


public class MessageHandler {
    private String message;
    private GameMode game;
    private String state = "NoGame";
    private HashMap<String, HashMap<String, Command>> stateDict = new HashMap<>();

    public MessageHandler(){
        stateDict.put("NoGame", new HashMap<>());
        stateDict.put("Game", new HashMap<>());
        stateDict.get("NoGame").put("y", new StartGame());
        stateDict.get("NoGame").put("n", new NoGame());
        stateDict.get("NoGame").put("l", new LoadQuestion());
        stateDict.get("NoGame").put("default", new IncorrectMessage());
        stateDict.get("Game").put("f", new FinishGame());
        stateDict.get("Game").put("n", new SkipQuestion());
        stateDict.get("Game").put("s", new SaveQuestion());
        stateDict.get("Game").put("default", new CheckAnswer());

    }

    public String handleMessage(String message) {
        this.message = message.toLowerCase().replaceAll(System.getProperty("line.separator"), " ").trim();

        return stateDict.get(state).containsKey(message)
                ? stateDict.get(state).get(message).DoSomething()
                : stateDict.get(state).getOrDefault("default", new IncorrectMessage()).DoSomething();
    }

    @VisibleForTesting
    public GameMode getGame (){
        return game;
    }

    public class StartGame implements Command {
        @Override
        public String DoSomething() {
            game = new SimplestGameMod(new SimplestQuestionsGenerator());
            state = "Game";
            return gameStartMessage + game.getQuestion();
        }
    }

    public class NoGame implements Command {
        @Override
        public String DoSomething() {
            game = null;
            state = "NoGame";
            return noGameMessage;
        }
    }

    public class FinishGame implements Command {
        @Override
        public String DoSomething() {
            game = null;
            state = "NoGame";
            return gameEndMessage;
        }
    }

    public class SkipQuestion implements Command {
        @Override
        public String DoSomething() {
            return game.Skip();
        }
    }

    public class IncorrectMessage implements Command {
        @Override
        public String DoSomething() {
            return incorrectInputMessage;
        }
    }

    public class CheckAnswer implements Command {
        @Override
        public String DoSomething() {
            return game.checkUserAnswer(message);
        }
    }

    public class LoadQuestion implements Command {
        @Override
        public String DoSomething() {
            return String.valueOf(FileSystemConnector.ReadQuestion(0, 0));
        }
    }

    public class SaveQuestion implements Command {
        @Override
        public String DoSomething()  {
            FileSystemConnector.WriteQuestion(game.getQuest(),0);
            return "Saved";
        }
    }
}
