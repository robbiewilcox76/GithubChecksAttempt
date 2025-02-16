import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.Test

public class MainTest {

    @Test
    public void testGreet() {
        Main main = new Main();
        String result = main.greet("JUnit");
        assertEquals("Hello, JUnit!", result);
    }
}