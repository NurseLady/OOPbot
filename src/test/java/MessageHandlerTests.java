import bot.MessageHandler;
import org.junit.Test;

import static bot.StringConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessageHandlerTests {
        private MessageHandler handler = new MessageHandler();

        @Test
        public void MessageHandler_noGame_test() {
            assertEquals(noGameMessage, handler.handleMessage("n"));
        }

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
            var game = handler.getGame();

            var questField = game.getQuest();

            assertEquals(gameStartMessage + questField, message);
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

            assertEquals(gameEndMessage, message);
        }
}
