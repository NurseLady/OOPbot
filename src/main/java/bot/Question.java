package bot;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int ID = 0;
    private String question;
    private String[] answers;
    int correctAnswerIndex;

    public Question(String question, String[] answers, int correctAnswerIndex){
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public Question(String question, String[] answers, int correctAnswerIndex, int ID){
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.ID = ID;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder(question + "\n\n");

        for (int i = 0; i < answers.length; i++)
            result.append(i + 1).append(". ").append(answers[i]).append("\n");

        return result.toString();
    }
}
