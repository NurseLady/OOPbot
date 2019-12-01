package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgBotStarter {
    public static void main(String[] args) {
        System.out.println("Starting tg bot...");
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TgBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
