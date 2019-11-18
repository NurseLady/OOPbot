package bot;


import java.util.HashMap;

interface StateEnum {
    HashMap<String, Command> getCommandDict();
}

public enum State implements StateEnum {
    MENU {
        public HashMap<String, Command> getCommandDict(){
            return new HashMap<>() {{
                put("y", Command.StartGame);
                put("s", Command.SelectGame);
                put("c", Command.CreateNewGame);
                put("default", Command.HandleIncorrectMessage);
                put("/start", Command.HandleStartMessage);
            }};
        }
    },
    GAME {
        public HashMap<String, Command> getCommandDict(){
            return new HashMap<>() {{
                put("f", Command.FinishGame);
                put("n", Command.SkipQuestion);
                put("default", Command.CheckAnswer);
            }};
        }
    },
    SELECT {
        public HashMap<String, Command> getCommandDict(){
            return new HashMap<>() {{
                put("default", Command.SetGameIndex);
            }};
        }
    },
    CREATE {
        public HashMap<String, Command> getCommandDict(){
            return new HashMap<>() {{
                //
            }};
        }
    };

    public String getCommandList(){
        StringBuilder result = new StringBuilder();
        for (var i : this.getCommandDict().keySet())
            if (!i.equals("default") && !i.equals("/start"))
                result.append(i) .append(" - ").append(this.getCommandDict().get(i).getCommandName()).append("\n");

        if (result.length() > 0)
            result.insert(0, "Комманды: \n");

        return result.append("\n").toString();
    }
}
