import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    void testValidateAgeThrowsException() {
        UserService service = new UserService();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.validateAge(16);
        });

        assertEquals("Underage", exception.getMessage());
    }
}
