package bot;

import dataClasses.CustomGameData;
import interfaces.GameMode;
import interfaces.QuestionsGenerator;

import static bot.StringConstants.*;

public class SimplestGameMod implements GameMode {
    private QuestionsGenerator generator;
    private Question quest;
    private int ID = 0;
    private String name = "Default Simplest Game";

    public  SimplestGameMod(QuestionsGenerator generator){ this.generator = generator; }

    public  SimplestGameMod(int ID, QuestionsGenerator generator, String gameName){
        this.generator = generator;
        this.ID = ID;
        name = gameName;
    }

    public SimplestGameMod(int ID, boolean isRandomOrder, CustomGameData gameData, String gameName) {
        this.ID = ID;
        generator = new CustomQuestionGenerator(isRandomOrder, gameData);
        name = gameName;
    }

    @Override
    public String getQuestion() {
        quest = generator.generateQuestion();
        return quest.toString();
    }

    @Override
    public String checkUserAnswer(String message) {
        try {
            if (Integer.parseInt(message) == quest.correctAnswerIndex + 1) {
                return correctAnswerMessage + this.Skip();
            }
        } catch (Exception er) { }

        return incorrectAnswerMessage;
    }

    @Override
    public String Skip() { return skipMessage + this.getQuestion(); }

    public Question getQuest() { return quest; }

    @Override
    public String getGameName() { return name; }

    @Override
    public int getGameID() { return ID; }
}
