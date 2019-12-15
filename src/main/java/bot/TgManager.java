package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

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

    public void sendMessageWithKeyboard(String state, String msg, long chatId){
        var keyboard = new ReplyKeyboardMarkup();
        var sendMessage = new SendMessage();
        List<KeyboardRow> keys = new ArrayList<>();

        sendMessage.setChatId(chatId);
        sendMessage.setText(msg);

        for (var i : CommandManager.getStateCommands(state))
            if (!i.name.equals("default") && !i.name.equals("/start")){
                var r = new KeyboardRow();
                r.add(i.toString());
                keys.add(r);
            }

        keyboard.setKeyboard(keys);
        sendMessage.setReplyMarkup(keyboard);
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        try {
            execute(sendMessage);
        } catch (TelegramApiException err){
            System.out.println("Ошибка при отправке клавиатуры:");
            err.printStackTrace();
        }
    }
}
