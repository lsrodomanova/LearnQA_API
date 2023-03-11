import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StingLength {

    @ParameterizedTest
    @ValueSource(strings = {"qwerty", "", "qwertyuiopasdfgh"})
    public void checkStringLength(String text) {
        int number =  text.length();
        assertTrue(number>15);
    }
}
