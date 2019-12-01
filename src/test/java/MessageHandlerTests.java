import bot.FileSystemConnector;
import bot.MessageHandler;
import bot.SimplestGameMod;
import bot.State;
import org.junit.After;
import org.junit.Test;

import static bot.StringConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessageHandlerTests {
    private MessageHandler handler = new MessageHandler();

    @Test
    public void MessageHandler_incorrectMessage_test() {
        assertEquals(incorrectInputMessage, handler.handleMessage("ndsfdsdf"));
    }

    @Test
    public void MessageHandler_startGameGameMod_test() {
        handler.handleMessage("y");

        assertNotNull(handler.getGame());
    }

    @Test
    public void MessageHandler_startGameMessage_test()  {
        var message = handler.handleMessage("y");

        var d = handler.getData();

        assertEquals(gameStartMessage + d.state.getCommandList() + d.gameList.get(d.gameIndex).getQuest().toString(), message);
    }

    @Test
    public void MessageHandler_nextQuestion_test() {
        handler.handleMessage("y");
        var message = handler.handleMessage("n");

        var gameField = handler.getGame();
        var questField = gameField.getQuest();

        assertEquals(skipMessage + questField, message);
    }

    @Test
    public void MessageHandler_finishGame_test(){
        handler.handleMessage("y");
        var message = handler.handleMessage("f");

        assertEquals(gameEndMessage + handler.getData().state.getCommandList(), message);
    }

    @Test
    public void MessageHandler_saveQuestion_test(){
        handler.handleMessage("y");

        assertEquals("Вопрос сохранен", handler.handleMessage("s"));
    }

    @Test
    public void MessageHandler_loadQuestionError_test(){
        FileSystemConnector.deleteQuestion(0);
        handler.handleMessage("l");

        assertEquals(errLoadMessage, handler.handleMessage("l"));
    }

    @Test
    public void MessageHandler_loadQuestionSuccess_test(){
        handler.handleMessage("y");
        handler.handleMessage("s");
        handler.handleMessage("f");
        handler.handleMessage("l");

        assertEquals(FileSystemConnector.readQuestion(0).toString(), handler.handleMessage("l"));
    }

    @Test
    public void MessageHandler_selectGame_test(){
        var d = handler.getData();
        StringBuilder result = new StringBuilder(State.SELECT.getCommandList());
        result.append("Выбери режим: \n\n");
        for (var i = 1; i <= d.gameList.size(); i++)
            result.append(i).append(". ").append(d.gameList.get(i - 1).getGameName()).append("\n");

        assertEquals(result.toString(), handler.handleMessage("s"));
    }

    @Test
    public void MessageHandler_setCorrectGameIndex_test(){
        var d = handler.getData();
        d.state = State.SELECT;

        assertEquals(changeGameModMessage + d.gameList.get(d.gameIndex).getGameName() + "\n\n" +
                State.MENU.getCommandList(), handler.handleMessage("1"));
    }

    @Test
    public void MessageHandler_setIncorrectGameIndex_test(){
        var d = handler.getData();
        d.state = State.SELECT;

        assertEquals(incorrectInputMessage, handler.handleMessage("3"));
    }

    @Test
    public void MessageHandler_setStringGameIndex_test(){
        var d = handler.getData();
        d.state = State.SELECT;

        assertEquals(incorrectInputMessage, handler.handleMessage("sdfsdfs"));
    }
}
