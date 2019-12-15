package bot;

import bot.dataClasses.UserInfo;
import bot.interfaces.DataBaseConnector;

import java.util.HashMap;

public class UserManager {
    private static HashMap<Long, UserInfo> users = new HashMap<>();
    private static DataBaseConnector connector = new FileSystemConnector();

    public static UserInfo getUserInfo(Long userId){
        UserInfo result = null;
        result = users.get(userId);
        if (result == null){
            result = connector.readUser(userId);
            users.put(userId, result);
        }

        return result;
    }

    public static void updateUserSave(UserInfo userInfo){
        connector.writeUser(userInfo);
    }
}
