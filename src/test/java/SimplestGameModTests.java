/*
import bot.SimplestGameMod;
import org.junit.Test;

import static bot.StringConstants.*;
import static org.junit.Assert.assertEquals;

public class SimplestGameModTests {
    @Test
    public void SimplestGameMod_correctAnswer_test(){
        var game = new SimplestGameMod(new TestQuestionGenerator());
        game.getQuestion();
        var message = game.checkUserAnswer("2");
        var questField = game.getQuest();

        assertEquals(correctAnswerMessage + skipMessage + questField, message);
    }

    @Test
    public void SimplestGameMod_incorrectAnswer_test() {
        var game = new SimplestGameMod(new TestQuestionGenerator());
        game.getQuestion();
        var message = game.checkUserAnswer("1");

        assertEquals(incorrectAnswerMessage, message);
    }

    @Test
    public void SimplestGameMod_StringAnswer_test() {
        var game = new SimplestGameMod(new TestQuestionGenerator());
        game.getQuestion();
        var message = game.checkUserAnswer("sdfsdf");

        assertEquals(incorrectAnswerMessage, message);
    }
}
*/
