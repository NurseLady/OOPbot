package bot.interfaces;

import bot.Question;
import bot.dataClasses.CustomGameData;

public interface DataBaseConnector {
    static boolean WriteQuestion(Question quest) { return false; }

    static Question ReadQuestion(int questID) { return null; }

    static boolean WriteGame() { return false; }

    static CustomGameData ReadGame(int GameID) { return null; }
}
