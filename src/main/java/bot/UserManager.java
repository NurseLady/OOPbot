package bot;

import bot.dataClasses.UserInfo;

import java.util.HashMap;

public class UserManager {
    private static HashMap<Long, UserInfo> users = new HashMap<>();

    public static UserInfo getUserInfo(Long userId){
        try{
            return users.get(userId);
        }
        catch (Exception ignored){
            users.put(userId, new UserInfo());
            return users.get(userId);
        }
    }
}
