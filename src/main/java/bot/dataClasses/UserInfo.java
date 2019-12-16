package bot.dataClasses;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public final long ID;
    public String state = "menu";
    public int gameKey = 1;
    public int score = 0;
    public String serviceCommandsInformation = ""; //тут служебная информация, необходимая для обработки следующей комманды.
                                                    // правильный ответ на вопрос, например

    public UserInfo(long ID){
        this.ID = ID;
    }
}
