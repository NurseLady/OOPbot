package bot;

import bot.commands.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class CommandManager {
    private static HashMap<String, HashSet<Command>> states = new HashMap<>();

    static {
        states.put("menu", new HashSet<>() {{
                add(new Play("y"));
                add(new SelectGame("s"));
                add(new CreateNewGame("c"));
                add(new Unknown("default"));
                add(new Start("/start"));
            }});
        states.put("game", new HashSet<>() {{
                add(new FinishGame("f"));
                add(new SkipQuestion("n"));
                add(new CheckAnswer("default"));
                add(new Start("/start"));
            }});
        states.put("select", new HashSet<>(){{
                add(new SetGame("default"));
                add(new Start("/start"));
            }});
    }

    public static HashMap<String, HashSet<Command>> getStates(){
        return states;
    }

    public static String stateToString(String state){
        StringBuilder result = new StringBuilder();
        for (var command : states.get(state))
            if (!command.name.equals("default") && !command.name.equals("/start"))
                result.append(command.toString()).append("\n");

        if (result.length() > 0)
            result.insert(0, "Комманды: \n");

        return result.append("\n").toString();
    }

    public static HashSet<Command> getStateCommands(String state){
        return states.get(state);
    }

    public static Command getCommand(Collection<Command> commands, String message) {
        String commandName = message.replaceAll(System.getProperty("line.separator"), " ")
                .split(" ")[0]
                .toLowerCase()
                .trim();
        for (Command command : commands) {
            if (command.name.equals(commandName)){
                return command;
            }
        }

        for (Command command : commands) {
            if (command.name.equals("default")){
                return command;
            }
        }

        return new Start("/start");
    }
}
