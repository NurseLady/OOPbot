package bot;

import DataClasses.MessageHandlerData;

import static bot.StringConstants.*;


interface CommandEnum {
    MessageHandlerData execute(MessageHandlerData data);
    String getCommandName();
}

public enum Command implements CommandEnum {
    StartGame{
        private String name = "Играть";

        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.state = State.GAME;
            d.message = gameStartMessage + d.state.getCommandList() + d.gameList.get(data.gameIndex).getQuestion();
            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }

    },
    CreateNewGame{
        private String name = "Создать новый режим [Disable]";
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.message = "Пока в разработке :(";
            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }
    },
    SelectGame{
        private String name = "Выбрать режим игры";

        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            StringBuilder result = new StringBuilder("Выбери режим: \n\n");
            var d = data.clone();

            for (var i = 1; i <= d.gameList.size(); i++)
                result.append(i).append(". ").append(d.gameList.get(i - 1).getGameName()).append("\n");

            d.state = State.SELECT;
            d.message = d.state.getCommandList() + result.toString();
            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }
    },
    HandleIncorrectMessage{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.message = incorrectInputMessage;
            return d;
        }

        @Override
        public String getCommandName() {
            return null;
        }
    },
    FinishGame{
        private String name = "Закончить игру";

        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.state = State.MENU;
            d.message = gameEndMessage + d.state.getCommandList();
            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }
    },
    SkipQuestion{
        private String name = "Пропустить вопрос";

        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.message = data.gameList.get(data.gameIndex).Skip();
            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }
    },
    CheckAnswer{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.message = data.gameList.get(data.gameIndex).checkUserAnswer(d.message);
            return d;
        }

        @Override
        public String getCommandName() {
            return null;
        }
    },
    HandleStartMessage{
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();
            d.state = State.MENU;
            d.message = new MessageHandler().getStartMessage();
            return d;
        }

        @Override
        public String getCommandName() {
            return null;
        }
    },
    SetGameIndex{
        private String name;
        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();

            try {
                if (Integer.parseInt(d.message) <= d.gameList.size()) {
                    d.gameIndex = Integer.parseInt(d.message) - 1;
                    d.state = State.MENU;
                    d.message = changeGameModMessage + d.gameList.get(d.gameIndex).getGameName() + "\n\n" +
                            d.state.getCommandList();
                    return d;
                }
            } catch (Exception ignored) { }

            d.message = incorrectInputMessage;
            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }
    },
    LoadQuestionText{
        private String name = "Загрузить текст сохраненного вопроса";

        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();

            try {
                d.message = FileSystemConnector.readQuestion(0).toString();
            } catch (Exception e) {
                d.message = errLoadMessage;
            }

            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }
    },
    SaveQuestion{
        private String name = "Сохранить вопрос";

        @Override
        public MessageHandlerData execute(MessageHandlerData data) {
            var d = data.clone();

            try {
                FileSystemConnector.writeQuestion(d.gameList.get(d.gameIndex).getQuest());
                d.message = "Вопрос сохранен";
            } catch (Exception e) {
                d.message = "Ой! Что-то пошло не так!";
            }

            return d;
        }

        @Override
        public String getCommandName() {
            return name;
        }
    }
}
