package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Scanner;

public class TelegramBot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotUsername() {
        return "QuizBot";
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update e) {
        MessageHandler chat = new MessageHandler();
        Scanner in = new Scanner(System.in);
        String message;

        System.out.println(StringConstants.startMessage);

        while (true){
            message = in.next();
            System.out.println(chat.handleMessage(message));
        }
        // t.me/qu12bot
    }

    @Override
    public String getBotToken() {
        return "903364057:AAFCgnAlrL1kpjMz89gr7BoGdj3GPz6II5w";
        //Токен бота
    }

}
