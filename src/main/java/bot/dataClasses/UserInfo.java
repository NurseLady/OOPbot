package bot.dataClasses;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public final long ID;
    public String state = "menu";
    public int gameKey = 1;
    public int score = 0;
    public String rightAnswer = "";

    public UserInfo(long ID){
        this.ID = ID;
    }
}
