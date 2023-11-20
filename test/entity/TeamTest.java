package entity;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TeamTest {

    private final Team testTeam;

    public TeamTest() {
        testTeam = new Team("TestTeamName", null, null, null);
    }

    @Test
    public void testTeamName() {
        assertEquals(testTeam.getTeamName(),"TestTeamName");
    }

    @Test
    public void testTeamTasks() {
        List<Todo> todoList = new ArrayList<>();
        Todo testTodo = new Todo("testTodo", "test todo description", LocalDateTime.now(),
                LocalDateTime.now(), "requester", "requestedTo", false);
        todoList.add(testTodo);
        testTeam.setTeamTasks(todoList);
        assertEquals(testTeam.getTeamTasks(), todoList);
    }

    @Test
    public void testTManagers() {
        List<String> managers = new ArrayList<>();
        managers.add("testManager1");
        managers.add("testManager2");
        testTeam.setManagers(managers);
        assertEquals(testTeam.getManagers(), managers);
    }

    @Test
    public void testMembers() {
        List<String> members = new ArrayList<>();
        members.add("testMember");
        testTeam.setMembers(members);
        assertEquals(testTeam.getMembers(), members);
    }
}
