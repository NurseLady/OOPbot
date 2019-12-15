package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TgBot extends TelegramLongPollingBot {
    private static String name;
    private static String token;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/tgbotconfig.properties"));
            name = prop.getProperty("name");
            token = prop.getProperty("token");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при загрузке файла конфигурации");
        }
    }

    @Override
    public void onUpdateReceived(Update e) {
        var message = e.getMessage();
        var chatId = message.getChatId();
        var userInfo = UserManager.getUserInfo(chatId);
        var text = message.getText();
        CommandManager.getCommand(CommandManager.getStateCommands(userInfo.state), text).exec(text, userInfo);
    }

    @Override
    public String getBotToken() { return token; }

    @Override
    public String getBotUsername() { return name; }

}
