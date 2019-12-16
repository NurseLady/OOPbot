package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
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
        Message message = null;
        String text = null;
        if (e.hasMessage()) {
            if(e.getMessage().hasText()){
                message = e.getMessage();
                text = message.getText();
            }
        } else if (e.hasCallbackQuery()) {
            var query = e.getCallbackQuery();
            message = query.getMessage();
            text = query.getData();
        }

        var chatId = message.getChatId();
        var userInfo = UserManager.getUserInfo(chatId);
        CommandManager.getCommand(CommandManager.getStateCommands(userInfo.state), text).exec(text, userInfo);
    }

    @Override
    public String getBotToken() { return token; }

    @Override
    public String getBotUsername() { return name; }

}
