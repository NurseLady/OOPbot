import java.util.Scanner;

public class ConsoleApp {
    public static void Main() {
        MessageHandler chat = new MessageHandler();
        chat.GetStartMessage();
        while (true){
            Scanner in = new Scanner(System.in);
            String message = in.next();
            chat.HandleMessage(message);
        }
    }
}
