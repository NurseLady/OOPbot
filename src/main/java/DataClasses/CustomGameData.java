package DataClasses;

import bot.Question;

import java.util.ArrayList;
import java.util.Collection;

public class CustomGameData {
    private ArrayList<Question> questionArrayList = new ArrayList<>();

    public CustomGameData(Collection<Question> questionsList){
        if (questionsList != null && !questionsList.isEmpty())
            questionArrayList.addAll(questionsList);
        else
            System.out.println("Ахтунг! Создана пустая игра!");
    }

    public CustomGameData(Question question){
        if (question != null)
                questionArrayList.add(question);
        else
            System.out.println("Ахтунг! Создана пустая игра!");
    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public void addQuestion(Question question){
        if (question != null)
            questionArrayList.add(question);
    }
}
