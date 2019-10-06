package bot;

import interfaces.GameMode;
import interfaces.QuestionsGenerator;

public class SimplestGameMod implements GameMode {
    private QuestionsGenerator generator = new SimplestQuestionsGenerator();
    private Question quest;
    private String skipMessage = "Хорошо, держи следующий вопрос: \n\n";
    private String correctAnswerMessage = "И это правильный ответ! ";
    private String uncorrectAnswerMessage = "Не верно, попробуй ещё разок!";


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
        return uncorrectAnswerMessage;
    }

    @Override
    public String Skip() {
        return skipMessage + this.getQuestion();
    }
}
