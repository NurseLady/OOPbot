package interfaces;

public interface IGameMode {
    String GetQuestion();
    String CheckUserAnswer(String message);
    String Skip();
}
