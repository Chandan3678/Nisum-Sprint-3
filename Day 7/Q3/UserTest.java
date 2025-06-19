import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserProperties() {
        User user = new User("John Doe", "john@example.com", 25);

        // Individual assertions
        assertTrue(user.getAge() > 18);
        assertNotNull(user.getEmail());

        // Grouped assertion
        assertAll("User Properties",
            () -> assertEquals("John Doe", user.getName()),
            () -> assertEquals("john@example.com", user.getEmail()),
            () -> assertEquals(25, user.getAge())
        );
    }
}
