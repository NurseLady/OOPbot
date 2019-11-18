package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    private MessageHandler chat = new MessageHandler();

    @Override
    public String getBotUsername() {
        return BotData.name;
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update e) {
        var message = e.getMessage();
        var sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(chat.handleMessage(message.getText()));
        try {
            execute(sendMessage);
        } catch (TelegramApiException err){
            err.printStackTrace();
        }
        // t.me/qu12bot
    }

    @Override
    public String getBotToken() {
        return BotData.token;
        //Токен бота
    }

}
