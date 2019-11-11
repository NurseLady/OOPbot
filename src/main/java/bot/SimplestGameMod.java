package bot;

import com.google.common.annotations.VisibleForTesting;
import interfaces.GameMode;
import interfaces.QuestionsGenerator;

import static bot.StringConstants.*;

public class SimplestGameMod implements GameMode {
    private QuestionsGenerator generator;
    private Question quest;

    public  SimplestGameMod(QuestionsGenerator generator){
        this.generator = generator;
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
}
