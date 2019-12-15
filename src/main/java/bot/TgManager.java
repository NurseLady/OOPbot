package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TgManager extends TgBot{
    public void sendMessage(String msg, long chatId){
        var sendMessage = new SendMessage();

        sendMessage.setChatId(chatId).setText(msg);

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


        for (var i : CommandManager.getStateCommands(state))
            if (!i.name.equals("default") && !i.name.equals("/start")){
                var r = new KeyboardRow();
                r.add(i.toString());
                keys.add(r);
            }

        keyboard.setKeyboard(keys).setSelective(true).setResizeKeyboard(true).setOneTimeKeyboard(true);

        sendMessage.setChatId(chatId).setText(msg).setReplyMarkup(keyboard);

        try {
            execute(sendMessage);
        } catch (TelegramApiException err){
            System.out.println("Ошибка при отправке ответа с клавиатурой:");
            err.printStackTrace();
        }
    }

    public void sendMessageWithInlineKeyboard(HashMap<String, String> buttons, String msg, long chatId){
        var inlineKeyboard =new InlineKeyboardMarkup();
        var sendMessage = new SendMessage();
        List<List<InlineKeyboardButton>> keys = new ArrayList<>();

        for (var i : buttons.keySet())
        {
            List<InlineKeyboardButton> r = new ArrayList<>();
            r.add(new InlineKeyboardButton().setText(buttons.get(i)).setCallbackData(i));
            keys.add(r);
        }

        inlineKeyboard.setKeyboard(keys);

        sendMessage.setChatId(chatId).setText(msg).setReplyMarkup(inlineKeyboard);

        try {
            execute(sendMessage);
        } catch (TelegramApiException err){
            System.out.println("Ошибка при отправке ответа с инлайн-клавиатурой:");
            err.printStackTrace();
        }
    }
}
