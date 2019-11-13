package interfaces;

import bot.Question;

public interface GameMode {
    String getQuestion();
    String checkUserAnswer(String message);
    String Skip();
    Question getQuest();
    String getGameName();
    int getGameID();
}
