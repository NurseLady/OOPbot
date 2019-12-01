package bot;

import DataClasses.MessageHandlerData;
import com.google.common.annotations.VisibleForTesting;
import interfaces.GameMode;

import java.util.ArrayList;

import static bot.StringConstants.startMessage;

public class MessageHandler {
    private final int ID = 0;
    private MessageHandlerData data;

    public MessageHandler(){
        int gameIndex = 0;
        ArrayList<GameMode> gameList = new ArrayList<>(){{
            add(GameFactory.getGame("Simplest", new SimplestQuestionsGenerator(),
                    "Default Simplest Game"));
        }};
        State state = State.MENU;
        data = new MessageHandlerData(ID, null, gameIndex, gameList, state);
    }

    public String handleMessage(String message) {
        data.message = message.toLowerCase().replaceAll(System.getProperty("line.separator"), " ").trim();

        var newData = data.state.getCommandDict().containsKey(message)
                ? data.state.getCommandDict().get(message).execute(data)
                : data.state.getCommandDict()
                            .getOrDefault("default", bot.Command.HandleIncorrectMessage)
                            .execute(data);

        data = newData.clone();

        return data.message;
    }

    public int getID(){ return ID; }

    public String getStartMessage(){
        return startMessage + data.state.getCommandList();
    };

    @VisibleForTesting
    public GameMode getGame () { return data.gameList.get(data.gameIndex); }

    @VisibleForTesting
    public MessageHandlerData getData() { return data; }
}
