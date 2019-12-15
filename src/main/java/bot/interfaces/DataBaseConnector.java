package bot.interfaces;

import bot.Question;
import bot.dataClasses.CustomGameData;
import bot.dataClasses.UserInfo;

public interface DataBaseConnector {
    boolean writeQuestion(Question quest);

    Question readQuestion(int questID);

    boolean deleteQuestion(int questID);

    boolean writeGame();

    CustomGameData readGame(int gameID);

    boolean deleteGame(int gameID);

    boolean writeUser(UserInfo userInfo);

    UserInfo readUser(long userID);

    boolean deleteUser(long userID);
}
