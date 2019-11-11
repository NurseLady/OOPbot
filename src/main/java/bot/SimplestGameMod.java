package bot;

import com.google.common.annotations.VisibleForTesting;
import interfaces.GameMode;
import interfaces.QuestionsGenerator;

import static bot.StringConstants.*;

public class SimplestGameMod implements GameMode {
    private QuestionsGenerator generator;
    private Question quest;
    private int ID = 0;

    public  SimplestGameMod(QuestionsGenerator generator){ this.generator = generator; }

    public  SimplestGameMod(QuestionsGenerator generator, int ID){
        this.generator = generator;
        this.ID = ID;
    }

    @Override
    public String getQuestion() {
        quest = generator.generateQuestion();
        return quest.toString();
    }

    @Override
    public String checkUserAnswer(String message) {

        if (Integer.parseInt(message) == quest.correctAnswerIndex + 1){
            return correctAnswerMessage + this.Skip();
        }
        return incorrectAnswerMessage;
    }

    @Override
    public String Skip() {
        return skipMessage + this.getQuestion();
    }

    @VisibleForTesting
    public Question getQuest() {
        return quest;
    }

    @Override
    public int getGameID() {
        return ID;
    }
}
