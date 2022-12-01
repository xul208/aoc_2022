import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DecemberFirstTest {
    @Test
    void produceCorrectResult() throws IOException {
        final int firstResult = DecemberFirst.findMaxCalories();
        final int secondResult = DecemberFirst.findMaxThreeCalories();
        assertEquals(firstResult, 71502);
        assertEquals(secondResult, 208191);
    }
}