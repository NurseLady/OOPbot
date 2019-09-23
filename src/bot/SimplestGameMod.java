package bot;

import interfaces.IGameMode;
import interfaces.IQuestionsGenerator;

public class SimplestGameMod implements IGameMode {
    private IQuestionsGenerator generator = new SimplestQuestionsGenerator();
    private Question quest;


    @Override
    public String GetQuestion() {
        quest = generator.GenerateQuestion();
        return quest.toString();
    }

    @Override
    public String CheckUserAnswer(String message) {

        if (message.compareTo(String.valueOf(quest.index + 1)) == 1){
            return "И это правильный ответ! " + this.Skip();
        }
        return "Не верно, попробуй ещё разок!";
    }

    @Override
    public String Skip() {
        return "Хорошо, держи следующий вопрос: \n\n" + this.GetQuestion();
    }
}
