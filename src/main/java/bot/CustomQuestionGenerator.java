package bot;

import bot.dataClasses.CustomGameData;
import bot.interfaces.QuestionsGenerator;

import java.util.ArrayList;
import java.util.Collections;

public class CustomQuestionGenerator implements QuestionsGenerator {
    private boolean isRandomOrder;
    private ArrayList<Question> questions;
    private ArrayList<Integer> order = new ArrayList<>();
    private int lastQuestionIndex = 0;

    public CustomQuestionGenerator(boolean isRandomOrder, CustomGameData gameData){
        this.isRandomOrder = isRandomOrder;
        questions = gameData.getQuestionArrayList();
        addIndexToOrder();
    }

    @Override
    public Question generateQuestion() {
        if (order.size() < questions.size())
            addIndexToOrder();

        if (order.size() <= lastQuestionIndex)
            lastQuestionIndex = 0;

        return questions.get(order.get(lastQuestionIndex++));
    }

    private void  addIndexToOrder(){
        var diff = questions.size() - order.size();
        var indexList = new ArrayList<Integer>();

        for (int i = 0; i < diff; i++)
            indexList.add(order.size() + i);

        if (!isRandomOrder)
            Collections.shuffle(indexList);

        order.addAll(indexList);
    }
}
