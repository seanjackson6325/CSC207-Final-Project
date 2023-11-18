package entity;

import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;

public class TodoTest {

    private final Todo testTodo;
    private final LocalDateTime now;

    public TodoTest() {
        now = LocalDateTime.now();
        testTodo = new Todo("testTodo", "test todo description", now,
                now, "requester", "requestedTo", true);
    }

    @Test
    public void testGetName() {
        assertEquals(testTodo.getName(), "testTodo");
    }

    @Test
    public void testGetDescription() {
        assertEquals(testTodo.getDescription(), "test todo description");
    }

    @Test
    public void testGetStartTime() {
        assertEquals(testTodo.getStartTime(), now);
    }

    @Test
    public void testGetEndTime() {
        assertEquals(testTodo.getEndTime(), now);
    }

    @Test
    public void testGetRequester() {
        assertEquals(testTodo.getRequester(), "requester");
    }

    @Test
    public void testGetRequestedTo() {
        assertEquals(testTodo.getRequestedTo(), "requestedTo");
    }

    @Test
    public void testGetStatus() {
        // by default if boolean is not set, it is false
        assert testTodo.getStatus();
    }
}
