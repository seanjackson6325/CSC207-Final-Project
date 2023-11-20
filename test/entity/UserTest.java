package entity;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private final User testUser;
    public UserTest() {
        testUser = new User("test name", "test password", null, null);
    }

    @Test
    public void testGetUsername() {
        assertEquals(testUser.getUsername(), "test name");
    }

    @Test
    public void testGetPassword() {
        assertEquals(testUser.getPassword(), "test password");
    }

    @Test
    public void testTaskList() {
        List<Todo> todoList = new ArrayList<>();
        Todo testTodo = new Todo("testTodo", "test todo description", LocalDateTime.now(),
                LocalDateTime.now(), "requester", "requestedTo", false);
        todoList.add(testTodo);
        testUser.setTaskList(todoList);
        assertEquals(testUser.getTaskList(), todoList);
    }

    @Test
    public void testTeams() {
        List<String> teams = new ArrayList<>();
        teams.add("testTeam1");
        testUser.setTeams(teams);
        assertEquals(testUser.getTeam(), teams);
    }
}
