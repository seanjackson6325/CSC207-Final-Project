package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Team;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.CompleteTodoTeam.CompleteTodoTeamInputData;
import use_case.CompleteTodoTeam.CompleteTodoTeamInteractor;
import use_case.CompleteTodoTeam.CompleteTodoTeamOutputBoundary;
import use_case.CompleteTodoTeam.CompleteTodoTeamOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CompleteTodoTeamInteractorTest {
    @Test
    public void testExecute() {

        DataAccess dataAccess = new DataAccess();
        try {
            Thread.sleep(2000);
            dataAccess.deleteTeam("testTeam210398129382103989081");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        } catch (Exception ignored) {

        }

        try {
            CompleteTodoTeamInteractor createUserInteractor = getCompleteTodoTeamInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            List<String> managerList = new ArrayList<>();
            List<String> memberList = new ArrayList<>();
            List<Todo> teamTasks = new ArrayList<>(List.of(new Todo("TestTodo", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false), new Todo("TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false)));
            Team testTeam = new Team("testTeam210398129382103989081", teamTasks, managerList, memberList);
            dataAccess.createUser(testuser);
            dataAccess.createTeam(testTeam);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(createUserInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            CompleteTodoTeamInputData inputData = new CompleteTodoTeamInputData(1, "TestTodo", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", "testTeam210398129382103989081");
            createUserInteractor.execute(inputData);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            CompleteTodoTeamInputData inputDataNoName = new CompleteTodoTeamInputData(1, "", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", "testTeam210398129382103989081");
            createUserInteractor.execute(inputDataNoName);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            CompleteTodoTeamInputData inputDataDates = new CompleteTodoTeamInputData(1, "TestTodo", "TestDesc", LocalDateTime.now().plusDays(6), LocalDateTime.now().plusDays(5), "testUser102983748912839", "testUser102983748912839", "testTeam210398129382103989081");
            createUserInteractor.execute(inputDataDates);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            CompleteTodoTeamInputData inputDataStartDate = new CompleteTodoTeamInputData(1, "TestTodo", "TestDesc", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", "testTeam210398129382103989081");
            createUserInteractor.execute(inputDataStartDate);

            Thread.sleep(2000);
            dataAccess.deleteTeam("testTeam210398129382103989081");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


    }

    @NotNull
    private static CompleteTodoTeamInteractor getCompleteTodoTeamInteractor(DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        CompleteTodoTeamOutputBoundary presenter = new CompleteTodoTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(CompleteTodoTeamOutputData createUserOutputData) {
                assertEquals(createUserOutputData.getMessage(), "Task Successfully Completed!");
                System.out.println("Success: " + createUserOutputData.getMessage());
            }

            @Override
            public void prepareFailView(String error) {
                if (!failure1[0]) {
                    assertEquals(error, "Task does not have a name!");
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals(error, "Invalid start & end dates!");
                    failure2[0] = true;
                } else if (!failure3[0]) {
                    assertEquals(error, "Invalid start date!");
                    failure3[0] = true;
                }
                System.out.println("Failure: " + error);
            }
        };

        return new CompleteTodoTeamInteractor(dataAccess, presenter);
    }
}