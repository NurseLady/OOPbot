import interfaces.IGameMode;

public class SimplestGameMod implements IGameMode {
    @Override
    public String GetQuestion() {
        return null;
    }

    @Override
    public String CheckUserAnswer(String message) {
        return null;
    }

    @Override
    public String Skip() {
        return null;
    }
}
