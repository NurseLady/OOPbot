package bot.interfaces;

import bot.Question;

public interface GameMode {
    Question getQuestion();
    boolean checkUserAnswer(String message, int correctAnswerIndex);
    Question Skip();
    String getName();
    int getGameID();
}
