package bot;

import java.util.*;

public class Question {
    public String question;
    public String[] answer;
    public int index;

    public Question(String question, String[] answer, int index){
        this.question = question;
        this.answer = answer;
        this.index = index;
    }

    @Override
    public String toString(){
        String result = question + "\n\n";
        for (int i = 0; i < 4; i++) result +=  (i + 1) + ". " + answer[i] + "\n";
        return result;
    }
    //public Map<Integer,Integer> question_answer = new HashMap<Integer, Integer>();
    /*public Map<String, Integer> AddQuestion(){
        int question = m.GenerateQuestion();
        int answer = m.GenerateAnswer();
        Map<String,Integer> question_answer = new HashMap<>();
        question_answer.put("Угадай число?", answer);
        int[] notanswer = new int[]{m.GenerateBait(), m.GenerateBait(), m.GenerateBait(), m.GenerateBait()};
        question_answer.put("3", notanswer[0]);
        question_answer.put("0", notanswer[1]);
        question_answer.put("1", notanswer[2]);
        question_answer.put("2", notanswer[3]);
        return question_answer;
    }*/
    /*public String ShowQuestion(){
        Map<String, Integer> qa = AddQuestion();
        String str = new String();
        str += qa.keySet();
        //str += qa.values();
        return str;
        }*/
}
