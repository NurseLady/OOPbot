package bot;

import interfaces.IQuestionsGenerator;
import java.util.*;
import java.io.*;

public class SimplestQuestionsGenerator implements IQuestionsGenerator {
    public Question GenerateQuestion(){
        Random rnd = new Random();
        String[] answers = new String[4];
        for (int i = 0; i < 4; i++){
            answers[i] = String.valueOf(rnd.nextInt(100));
        }
        int index = rnd.nextInt(4);
        String question = "Угадай моё чиселко";
        return  new Question(question, answers, index);
    }
}
