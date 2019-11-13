package DataClasses;

import bot.State;
import interfaces.GameMode;

import java.util.ArrayList;

public class MessageHandlerData {
    public final int ID;
    public String message;
    public int gameIndex;
    public ArrayList<GameMode> gameList;
    public State state;

    public MessageHandlerData(int ID, String message, int gameIndex, ArrayList<GameMode> gameList, State state){
        this.ID = ID;
        this.message = message;
        this.gameIndex = gameIndex;
        this.gameList = gameList;
        this.state = state;
    }

    public MessageHandlerData clone(){
        return new MessageHandlerData(ID, message, gameIndex, (ArrayList<GameMode>) gameList.clone(), state);
    }
}
