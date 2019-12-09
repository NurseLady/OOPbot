package bot.dataClasses;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public String state = "Menu";
    public int gameKey = -1;
    public String serviceCommandsInformation = null; //тут служебная информация, необходимая для обработки следующей комманды.
                                                    // правильный ответ на вопрос, например
}
