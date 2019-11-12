package interfaces;

import bot.Question;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataBaseConnector {
    static void WriteQuestion(Question quest, int groupID) {
        return;
    }

    static Question ReadQuestion(int groupID, int questID) {
        return null;
    };
}
