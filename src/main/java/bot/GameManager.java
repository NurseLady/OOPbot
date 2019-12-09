package bot;

import bot.interfaces.GameMode;

import java.util.HashMap;

public class GameManager {
    private static HashMap<Integer, GameMode> games = new HashMap<>();

    static {
        games.put(1, new SimplestGameMod(new SimplestQuestionsGenerator(), "Default Simplest Game"));
    }

    public static HashMap<Integer, GameMode> getGames(){
        return games;
    }

    public static GameMode getGame(int key){
        return games.get(key);
    }

    public static String getGamesList(){
        StringBuilder result = new StringBuilder();
        for (var i : games.keySet())
            result.append(i).append(". ").append(games.get(i).getName()).append("\n");

        if (result.length() > 0)
            result.insert(0, "Режимы игры: \n");

        return result.append("\n").toString();
    }
}
