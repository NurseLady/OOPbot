package bot;

public class Question {
    private String question;
    private String[] answers;
    int correctAnswerIndex;

    Question(String question, String[] answers, int correctAnswerIndex){
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder(question + "\n\n");

        for (int i = 0; i < 4; i++)
            result.append(i + 1).append(". ").append(answers[i]).append("\n");

        return result.toString();
    }
}
