package bot;

import DataClasses.CustomGameData;
import interfaces.GameMode;
import interfaces.QuestionsGenerator;

import java.util.Collection;

public class GameFactory {
    private static int ID = 0;
    public static GameMode getGame(String gameType, boolean isRandomOrder, Collection<Question> questions, String gameName){
        switch (gameType){
            case "Simplest":
                return new SimplestGameMod(ID++, isRandomOrder, new CustomGameData(questions), gameName);
            default:
                System.out.println("Неизвестный тип игры");
                return null;
        }
    }

    public static GameMode getGame(String gameType, QuestionsGenerator generator, String gameName){
        switch (gameType){
            case "Simplest":
                return new SimplestGameMod(ID++, generator, gameName);
            default:
                System.out.println("Неизвестный тип игры");
                return null;
        }
    }

    public static int getID(){
        return ID - 1;
    }
}
