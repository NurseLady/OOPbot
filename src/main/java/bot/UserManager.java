package bot;

import bot.dataClasses.UserInfo;

import java.util.HashMap;

public class UserManager {
    private static HashMap<Long, UserInfo> users = new HashMap<>();

    public static UserInfo getUserInfo(Long userId){
        UserInfo result;
        try{
            result = users.get(userId);
            if (result == null){
                users.put(userId, new UserInfo());
                result = users.get(userId);
            }
        }
        catch (Exception ignored){
            users.put(userId, new UserInfo());
            result = users.get(userId);
        }

        return result;
    }
}
