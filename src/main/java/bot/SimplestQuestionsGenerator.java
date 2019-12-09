package bot;

import bot.interfaces.QuestionsGenerator;

import java.util.Random;

public class SimplestQuestionsGenerator implements QuestionsGenerator {
    private final String question = "Угадай моё чиселко";

    public Question generateQuestion(){
        Random rnd = new Random();
        String[] answers = new String[4];
        for (int i = 0; i < 4; i++){
            answers[i] = String.valueOf(rnd.nextInt(100));
        }
        int index = rnd.nextInt(4);
        return  new Question(question, answers, index);
    }
}
