import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DecemberSecondTest {
    @Test
    void produceCorrectResult() throws IOException {
        final int firstResult = DecemberSecond.calculatePlanPoints(DecemberSecond::parseLineAsGestures);

        assertEquals(firstResult, 14264);
    }
}