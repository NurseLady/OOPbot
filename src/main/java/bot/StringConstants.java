package bot;

public class StringConstants {
    public static String startMessage = "Привет! Я бот, с которым можно поиграть. Начать игру? (y/n)";
    public static String gameStartMessage = "Игра началась!\n" +
            "Если вопрос слишком сложный, отправь \"n\" и я его пропущу.\n" +
            "Когда игра надоест, отправь \"f\" и она закончится.\n\n";
    public static String gameEndMessage = "Игра окончена. Хочешь поиграть ещё? (y/n)";
    public static String noGameMessage = "Отправь \"y\" если передумаешь.";
    public static String incorrectInputMessage = "Я таких слов не знаю!";

    public static String skipMessage = "Хорошо, держи следующий вопрос: \n\n";
    public static String correctAnswerMessage = "И это правильный ответ! ";
    public static String incorrectAnswerMessage = "Не верно, попробуй ещё разок!";
}