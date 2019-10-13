package bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        MessageHandler chat = new MessageHandler();
        Scanner in = new Scanner(System.in);
        String message;

        System.out.println(StringConstants.startMessage);

        while (true){
            message = in.next();
            System.out.println(chat.handleMessage(message));
        }
    }
}
