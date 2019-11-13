package bot;

import DataClasses.MessageHandlerData;

import static bot.StringConstants.*;


interface CommandEnum {
    MessageHandlerData execute(MessageHandlerData data);
}

public enum Command implements CommandEnum {
    StartGame{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.state = State.GAME;
            d.message = gameStartMessage + data.gameList.get(data.gameIndex).getQuestion();
            return d;
        }

    },
    DontStartGame{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.state = State.MENU;
            d.message = noGameMessage;
            return d;
        }
    },
    CreateNewGame{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            return null;
        }
    },
    SelectGame{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            StringBuilder result = new StringBuilder("Выбери режим: \n\n");
            var d = data.clone();

            for (var i = 1; i <= d.gameList.size(); i++)
                result.append(i).append(". ").append(d.gameList.get(i - 1).getGameName()).append("\n");

            d.state = State.SELECT;
            d.message = result.toString();
            return d;
        }
    },
    HandleIncorrectMessage{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.message = incorrectInputMessage;
            return d;
        }
    },
    FinishGame{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.state = State.MENU;
            d.message = gameEndMessage;
            return d;
        }
    },
    SkipQuestion{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.message = data.gameList.get(data.gameIndex).Skip();
            return d;
        }
    },
    CheckAnswer{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.message = data.gameList.get(data.gameIndex).checkUserAnswer(d.message);
            return d;
        }
    },
    SetGameIndex{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();

            try {
                if (Integer.parseInt(d.message) <= d.gameList.size()) {
                    d.gameIndex = Integer.parseInt(d.message) - 1;
                    d.state = State.MENU;
                    d.message = changeGameModMessage + d.gameList.get(d.gameIndex).getGameName() + "\n\n" +
                            startMessage;
                    return d;
                }
            } catch (Exception ignored) { }

            d.message = incorrectInputMessage;
            return d;
        }
    }
}
