import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Needed for @BeforeAll and @AfterAll on non-static methods
public class LifecycleTest {

    @BeforeAll
    void beforeAll() {
        System.out.println(">> @BeforeAll - Executes once before all tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("  > @BeforeEach - Executes before each test method");
    }

    @Test
    vo
