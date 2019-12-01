package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TgBot extends TelegramLongPollingBot {
    private MessageHandler handler = new MessageHandler();
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
    public String getBotUsername() {
        return name;
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update e) {
        var message = e.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(handler.handleMessage(message.getText()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException err){
            err.printStackTrace();
        }
        // t.me/qu12bot
    }

    @Override
    public String getBotToken() {
        return token;
        //Токен бота
    }

}
