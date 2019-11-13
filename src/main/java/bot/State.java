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
                put("n", Command.DontStartGame);
                put("s", Command.SelectGame);
                put("default", Command.HandleIncorrectMessage);
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
}
