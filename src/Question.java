import java.util.*;

public class Question {
    public SimplestQuestionsGenerator m = new SimplestQuestionsGenerator();
    //public int question = m.GenerateQuestion();
    //public int answer = m.GenerateAnswer();
    //public Map<Integer,Integer> question_answer = new HashMap<Integer, Integer>();
    public Map<Integer, Integer> AddQuestion(){
        int question = m.GenerateQuestion();
        int answer = m.GenerateAnswer();
        Map<Integer,Integer> question_answer = new HashMap<Integer, Integer>();
        question_answer.put(question, answer);
        int[] notanswer = new int[]{m.GenerateBait(), m.GenerateBait(), m.GenerateBait(), m.GenerateBait()};
        question_answer.put(3, notanswer[0]);
        question_answer.put(0, notanswer[1]);
        question_answer.put(1, notanswer[2]);
        question_answer.put(2, notanswer[3]);
        return question_answer;
    }
    public String ShowQuestion(){
        Map<Integer, Integer> qa = AddQuestion();
        String str = new String();
        str += qa.keySet();
        str += qa.values();
        return str;
        }
}
