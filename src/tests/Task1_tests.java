package tests;

import bot.MessageHandler;
import bot.Question;
import org.junit.*;

import static org.junit.Assert.*;

public class Task1_tests {
    private MessageHandler handler = new MessageHandler();
    
    @Test
    public void Question_ToString_test(){
        var quest = new Question("q", new String[] {"a", "b", "c"}, 1);
        assertEquals("q\n\n" + "1. a\n2. b\n3. c\n", quest.toString());
    }

    @Test
    public void MessageHandler_noGame_test() throws NoSuchFieldException, IllegalAccessException {
        var field = handler.getClass().getDeclaredField("noGameMessage");
        field.setAccessible(true);

        assertEquals(field.get(handler) , handler.handleMessage("n"));
    }

    @Test
    public void MessageHandler_incorrectMessage_test() throws NoSuchFieldException, IllegalAccessException {
        var field = handler.getClass().getDeclaredField("incorrectInputMessage");
        field.setAccessible(true);

        assertEquals(field.get(handler) , handler.handleMessage("ndsfdsdf"));
    }

    @Test
    public void MessageHandler_startGameGameMod_test() throws NoSuchFieldException, IllegalAccessException {
        handler.handleMessage("y");
        var field = handler.getClass().getDeclaredField("game");
        field.setAccessible(true);

        assertNotNull(field.get(handler));
    }

    @Test
    public void MessageHandler_startGameMessage_test() throws NoSuchFieldException, IllegalAccessException {
        var message = handler.handleMessage("y");
        var gameField = handler.getClass().getDeclaredField("game");
        gameField.setAccessible(true);
        var game = gameField.get(handler);

        var questField = game.getClass().getDeclaredField("quest");
        questField.setAccessible(true);

        var messageField = handler.getClass().getDeclaredField("gameStartMessage");
        messageField.setAccessible(true);

        assertEquals((String) messageField.get(handler) + questField.get(game), message);
    }

    @Test
    public void MessageHandler_nextQuestion_test() throws NoSuchFieldException, IllegalAccessException{
        handler.handleMessage("y");
        var message = handler.handleMessage("n");

        var gameField = handler.getClass().getDeclaredField("game");
        gameField.setAccessible(true);
        var game = gameField.get(handler);

        var questField = game.getClass().getDeclaredField("quest");
        questField.setAccessible(true);

        var skipMessageField = game.getClass().getDeclaredField("skipMessage");
        skipMessageField.setAccessible(true);

        assertEquals((String) skipMessageField.get(game) + questField.get(game), message);
    }

    @Test
    public void MessageHandler_finishGame_test() throws NoSuchFieldException, IllegalAccessException{
        handler.handleMessage("y");
        var message = handler.handleMessage("f");

        var messageField = handler.getClass().getDeclaredField("gameEndMessage");
        messageField.setAccessible(true);

        assertEquals(messageField.get(handler), message);
    }
}
