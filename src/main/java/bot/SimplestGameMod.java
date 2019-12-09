package bot;

import bot.dataClasses.CustomGameData;
import bot.interfaces.GameMode;
import bot.interfaces.QuestionsGenerator;

public class SimplestGameMod implements GameMode {
    private static int lastID = -1;
    private QuestionsGenerator generator;
    private int ID;
    private String name;

    public SimplestGameMod(QuestionsGenerator generator, String name){
        this.generator = generator;
        this.ID = ++lastID;
        this.name = name;
    }

    public SimplestGameMod(boolean isRandomOrder, CustomGameData gameData, String name) {
        this.ID = ++lastID;
        generator = new CustomQuestionGenerator(isRandomOrder, gameData);
        this.name = name;
    }

    @Override
    public Question getQuestion() {
        return generator.generateQuestion();
    }

    @Override
    public boolean checkUserAnswer(String message, int ans) {
        try {
            if (Integer.parseInt(message) == ans + 1) {
                return true;
            }
        } catch (Exception ignored) { }

        return false;
    }

    @Override
    public Question Skip() { return this.getQuestion(); }

    @Override
    public int getGameID() { return ID; }

    @Override
    public String getName() { return name; }
}