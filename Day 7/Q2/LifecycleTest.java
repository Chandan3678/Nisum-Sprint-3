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
    void testOne() {
        System.out.println("    >> @Test - testOne is running");
    }

    @Test
    void testTwo() {
        System.out.println("    >> @Test - testTwo is running");
    }

    @AfterEach
    void afterEach() {
        System.out.println("  > @AfterEach - Executes after each test method");
    }

    @AfterAll
    void afterAll() {
        System.out.println(">> @AfterAll - Executes once after all tests");
    }
}
