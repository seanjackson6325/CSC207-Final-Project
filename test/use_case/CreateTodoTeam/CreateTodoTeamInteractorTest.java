package use_case.CreateTodoTeam;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Team;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateTodoTeamInteractorTest {
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
            CreateTodoTeamInteractor createTeamInteractor = getCreateTodoTeamInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            List<String> managerList = new ArrayList<>();
            List<String> memberList = new ArrayList<>();
            Team testTeam = new Team("testTeam210398129382103989081", todoList, managerList, memberList);
            dataAccess.createUser(testuser);
            dataAccess.createTeam(testTeam);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(createTeamInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            CreateTodoTeamInputData inputData = new CreateTodoTeamInputData("TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testTeam210398129382103989081", true, "testTeam210398129382103989081");
            createTeamInteractor.execute(inputData);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoTeamInputData inputDataExists = new CreateTodoTeamInputData("TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testTeam210398129382103989081", true, "testTeam210398129382103989081");
            createTeamInteractor.execute(inputDataExists);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoTeamInputData inputDataNoName = new CreateTodoTeamInputData("", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testTeam210398129382103989081", true, "testTeam210398129382103989081");
            createTeamInteractor.execute(inputDataNoName);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoTeamInputData inputDataDates = new CreateTodoTeamInputData("TestTodo2", "TestDesc", LocalDateTime.now().plusDays(6), LocalDateTime.now().plusDays(5), "testTeam210398129382103989081", true, "testTeam210398129382103989081");
            createTeamInteractor.execute(inputDataDates);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoTeamInputData inputDateStartDate = new CreateTodoTeamInputData("TestTodo3", "TestDesc", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(6), "testTeam210398129382103989081", true, "testTeam210398129382103989081");
            createTeamInteractor.execute(inputDateStartDate);

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
    private static CreateTodoTeamInteractor getCreateTodoTeamInteractor(DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        final boolean[] failure4 = {false};
        CreateTodoTeamOutputBoundary presenter = new CreateTodoTeamOutputBoundary() {
            @Override
            public void successView(CreateTodoTeamOutputData createTeamOutputData) {
                assertEquals(createTeamOutputData.getMessage(), "Task Successfully Created!");
                System.out.println("Success: " + createTeamOutputData.getMessage());
            }

            @Override
            public void failureView(CreateTodoTeamOutputData createTeamOutputData) {

                if (!failure1[0]) {
                    assertEquals(createTeamOutputData.getMessage(), "There is another task with the same name!");
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals(createTeamOutputData.getMessage(), "Task does not have a name!");
                    failure2[0] = true;
                } else if (!failure3[0]) {
                    assertEquals(createTeamOutputData.getMessage(), "Invalid start & end dates!");
                    failure3[0] = true;
                } else if (!failure4[0]) {
                    assertEquals(createTeamOutputData.getMessage(), "Invalid start date!");
                    failure4[0] = true;
                }
                System.out.println("Failure: " + createTeamOutputData.getMessage());
            }
        };

        return new CreateTodoTeamInteractor(dataAccess, presenter);
    }


}
