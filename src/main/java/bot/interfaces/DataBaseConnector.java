package bot.interfaces;

import bot.Question;
import bot.dataClasses.CustomGameData;
import bot.dataClasses.UserInfo;

public interface DataBaseConnector {

    boolean writeUser(UserInfo userInfo);

    UserInfo readUser(long userID);
}
