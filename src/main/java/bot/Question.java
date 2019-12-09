package bot;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int lastID = -1;
    public final int ID;
    private String question;
    private String[] answers;
    public int correctAnswerIndex;

    public Question(String question, String[] answers, int correctAnswerIndex){
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        ID = ++lastID;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder(question + "\n");

        for (int i = 0; i < answers.length; i++)
            result.append(i + 1).append(". ").append(answers[i]).append("\n");

        return result.toString();
    }
}
