package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgManager extends TgBot{
    public void sendMessage(String msg, long chatId){
        var sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);
        try {
            execute(sendMessage);
        } catch (TelegramApiException err){
            System.out.println("Ошибка при отправке ответа:");
            err.printStackTrace();
        }
    }

    public void sendKeyboard(long chatId){

    }
}
