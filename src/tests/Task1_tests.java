package tests;

import bot.MessageHandler;
import bot.Question;
import bot.SimplestGameMod;
import interfaces.QuestionsGenerator;
import org.junit.*;

import static org.junit.Assert.*;
import static bot.StringConstants.*;

public class Task1_tests {
    private class TestQuestionGenerator implements QuestionsGenerator{
        private int count = 0;
        @Override
        public Question generateQuestion() {
            count++;
            return new Question(String.valueOf(count), new String[] {"a", "b", "c"}, 1);
        }
    }

    private MessageHandler handler = new MessageHandler();
    private TestQuestionGenerator generator = new TestQuestionGenerator();

    @Test
    public void Question_ToString_test(){
        assertEquals("1\n\n" + "1. a\n2. b\n3. c\n", generator.generateQuestion().toString());
    }

    @Test
    public void MessageHandler_noGame_test() {
        assertEquals(noGameMessage, handler.handleMessage("n"));
    }

    @Test
    public void MessageHandler_incorrectMessage_test() {
        assertEquals(incorrectInputMessage, handler.handleMessage("ndsfdsdf"));
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

        assertEquals(gameStartMessage + questField.get(game), message);
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

        assertEquals(skipMessage + questField.get(game), message);
    }

    @Test
    public void MessageHandler_finishGame_test(){
        handler.handleMessage("y");
        var message = handler.handleMessage("f");

        assertEquals(gameEndMessage, message);
    }

    @Test
    public void SimplestGameMod_correctAnswer_test() throws NoSuchFieldException, IllegalAccessException{
        var game = new SimplestGameMod(new TestQuestionGenerator());
        game.getQuestion();

        var questField = game.getClass().getDeclaredField("quest");
        questField.setAccessible(true);;

        var message = game.checkUserAnswer("2");

        assertEquals(correctAnswerMessage + skipMessage + questField.get(game), message);
    }

    @Test
    public void SimplestGameMod_uncorrectAnswer_test() {
        var game = new SimplestGameMod(new TestQuestionGenerator());
        game.getQuestion();

        var message = game.checkUserAnswer("1");

        assertEquals(uncorrectAnswerMessage, message);
    }
}
