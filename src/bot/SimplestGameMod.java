package bot;

import interfaces.GameMode;
import interfaces.QuestionsGenerator;

public class SimplestGameMod implements GameMode {
    private QuestionsGenerator generator = new SimplestQuestionsGenerator();
    private Question quest;


    @Override
    public String getQuestion() {
        quest = generator.generateQuestion();
        return quest.toString();
    }

    @Override
    public String checkUserAnswer(String message) {

        if (message.compareTo(String.valueOf(quest.correctAnswerIndex + 1)) == 1){
            return "И это правильный ответ! " + this.Skip();
        }
        return "Не верно, попробуй ещё разок!";
    }

    @Override
    public String Skip() {
        return "Хорошо, держи следующий вопрос: \n\n" + this.getQuestion();
    }
}
