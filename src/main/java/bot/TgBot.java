package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TgBot extends TelegramLongPollingBot {
    private String name;
    private String token;

    public TgBot(){
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
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(CommandManager.getCommand(CommandManager.getStateCommands(userInfo.state), text).exec(text, userInfo));
        try {
            execute(sendMessage);
        } catch (TelegramApiException err){
            System.out.println("Ошибка при отправке ответа:");
            err.printStackTrace();
        }
    }

    @Override
    public String getBotToken() { return token; }

    @Override
    public String getBotUsername() { return name; }

}
