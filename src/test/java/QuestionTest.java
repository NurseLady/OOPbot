import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuestionTest {
    private TestQuestionGenerator generator = new TestQuestionGenerator();

    @Test
    public void Question_ToString_test(){
        assertEquals("1\n\n" + "1. a\n2. b\n3. c\n", generator.generateQuestion().toString());
    }
}
