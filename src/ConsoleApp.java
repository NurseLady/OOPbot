import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        MessageHandler chat = new MessageHandler();
        Scanner in = new Scanner(System.in);
        String message;

        System.out.println(chat.GetStartMessage());

        while (true){
            message = in.next();
            System.out.println(chat.HandleMessage(message));
        }
    }
}
