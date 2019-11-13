package bot;

public class QuestionFactory {
    private static int ID = 0;

    public static Question getQuestion(String question, String[] answers, int correctAnswerIndex){
        return new Question(question, answers, correctAnswerIndex, ID++);
    }

    public static int getID(){
        return ID - 1;
    }
}
