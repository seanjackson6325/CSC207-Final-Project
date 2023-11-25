package data_access;

import entity.Todo;
import entity.User;
import entity.Team;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class DataAccessTest {

    private List<User> users;
    private List<Todo> todos;
    private List<Team> teams;
    private DataAccess dataAccess;


    // Note that DataAccess contains ImageKitAPI.upload() and donwload(), ensure ImageKitAPI is correct before running


    public DataAccessTest() {

        todos = new ArrayList<>();
        todos.add(new Todo(
                "testtodo1",
                "this is a test todo 1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                "testuser1_specialcase_1956129837469872134",
                "testuser2_specialcase_1956129837469872134",
                false
        ));
        todos.add(new Todo(
                "testtodo2",
                "this is a test todo 2",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                "testuser2_specialcase_1956129837469872134",
                "testuser1_specialcase_1956129837469872134",
                true
        ));
        users = new ArrayList<>();
        List<String> testTeamList = new ArrayList<>();
        testTeamList.add("testteam1_specialcase_1956129837469872134");
        testTeamList.add("testteam2_specialcase_1956129837469872134");
        users.add(new User(
                "testuser1_specialcase_1956129837469872134",
                "testuser1ps",
                todos,
                testTeamList
        ));
        users.add(new User(
                "testuser2_specialcase_1956129837469872134",
                "testuser2ps",
                todos,
                testTeamList
        ));
        teams = new ArrayList<>();
        List<String> testUserList = new ArrayList<>();
        testUserList.add("testuser1_specialcase_1956129837469872134");
        testUserList.add("testuser2_specialcase_1956129837469872134");
        List<String> testUserList2 = new ArrayList<>();
        testUserList2.add("testuser3_specialcase_1956129837469872134");
        testUserList2.add("testuser4_specialcase_1956129837469872134");
        teams.add(new Team(
                "testteam1_specialcase_1956129837469872134",
                todos,
                testUserList,
                testUserList2
        ));
        teams.add(new Team(
                "testteam2_specialcase_1956129837469872134",
                todos,
                testUserList2,
                testUserList
        ));

        dataAccess = new DataAccess();
    }


    @Test
    public void testInitialization() {
        assertNotNull(dataAccess.getData());
    }


    @Test
    public void testCreateAndReadUser() {
        dataAccess.createUser(users.get(0));
        dataAccess.createUser(users.get(1));
        User user1 = dataAccess.readUser(users.get(0).getUsername());
        User user2 = dataAccess.readUser(users.get(1).getUsername());

        assertEquals(user1.getUsername(), users.get(0).getUsername());
        assertEquals(user1.getPassword(), users.get(0).getPassword());
        assertEquals(user1.getTeam(), users.get(0).getTeam());
        assertEquals(user1.getTaskList().get(0).getName(), users.get(0).getTaskList().get(0).getName());

        assertEquals(user2.getUsername(), users.get(1).getUsername());
        assertEquals(user2.getPassword(), users.get(1).getPassword());
        assertEquals(user2.getTeam(), users.get(1).getTeam());
        assertEquals(user2.getTaskList().get(0).getName(), users.get(1).getTaskList().get(0).getName());
    }

    @Before
    public void waitFor2() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test
    public void testUpdateUser() {
        List<String> updateTeamList = new ArrayList<>();
        updateTeamList.add("testteam3_specialcase_1956129837469872134");
        updateTeamList.add("testteam4_specialcase_1956129837469872134");
        users.get(0).setTeams(updateTeamList);
        dataAccess.updateUser(users.get(0));
        User updatedUser = dataAccess.readUser(users.get(0).getUsername());

        assertEquals(updatedUser.getTeam(), users.get(0).getTeam());
    }


    @Test
    public void testDeleteUser() {
        dataAccess.deleteUser(users.get(0).getUsername());
        dataAccess.deleteUser(users.get(1).getUsername());

        assertNull(dataAccess.readUser("testuser1_specialcase_1956129837469872134"));
        assertNull(dataAccess.readUser("testuser2_specialcase_1956129837469872134"));
    }


    @Test
    public void testCreateAndReadTeam() {
        dataAccess.createTeam(teams.get(0));
        dataAccess.createTeam(teams.get(1));
        Team team1 = dataAccess.readTeam(teams.get(0).getTeamName());
        Team team2 = dataAccess.readTeam(teams.get(1).getTeamName());

        assertEquals(team1.getTeamName(), teams.get(0).getTeamName());
        assertEquals(team1.getMembers(), teams.get(0).getMembers());
        assertEquals(team1.getManagers(), teams.get(0).getManagers());
        assertEquals(team1.getTeamTasks().get(0).getName(), teams.get(0).getTeamTasks().get(0).getName());

        assertEquals(team2.getTeamName(), teams.get(1).getTeamName());
        assertEquals(team2.getMembers(), teams.get(1).getMembers());
        assertEquals(team2.getManagers(), teams.get(1).getManagers());
        assertEquals(team2.getTeamTasks().get(0).getName(), teams.get(1).getTeamTasks().get(0).getName());
    }



    @Test
    public void testUpdateTeam() {
        List<String> updateMemberList = new ArrayList<>();
        updateMemberList.add("testuser3_specialcase_1956129837469872134");
        updateMemberList.add("testuser4_specialcase_1956129837469872134");
        teams.get(0).setMembers(updateMemberList);
        dataAccess.updateTeam(teams.get(0));
        Team updatedUser = dataAccess.readTeam(teams.get(0).getTeamName());

        assertEquals(updatedUser.getMembers(), teams.get(0).getMembers());
    }


    @Test
    public void testDeleteTeam() {
        dataAccess.deleteTeam(teams.get(0).getTeamName());
        dataAccess.deleteTeam(teams.get(1).getTeamName());

        assertNull(dataAccess.readTeam("testteam1_specialcase_1956129837469872134"));
        assertNull(dataAccess.readTeam("testteam2_specialcase_1956129837469872134"));
    }

}
