package interfaces;

public interface GameMode {
    String getQuestion();
    String checkUserAnswer(String message);
    String Skip();
}
