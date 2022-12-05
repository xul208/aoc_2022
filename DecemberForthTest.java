import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecemberForthTest {
    @Test
    void produceCorrectAnswer() throws IOException {
        assertEquals(Util.calculatePointSum(DecemberForth::countTouch, "12_4_test.txt"), 4);
    }
}
