package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Team;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.DeleteTodoTeam.DeleteTodoTeamInputData;
import use_case.DeleteTodoTeam.DeleteTodoTeamInteractor;
import use_case.DeleteTodoTeam.DeleteTodoTeamOutputBoundary;
import use_case.DeleteTodoTeam.DeleteTodoTeamOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeleteTodoTeamInteractorTest {
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
            DeleteTodoTeamInteractor createUserInteractor = getDeleteTodoTeamInteractor(dataAccess);

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
            DeleteTodoTeamInputData inputData = new DeleteTodoTeamInputData(1,  "testTeam210398129382103989081");
            createUserInteractor.execute(inputData);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            DeleteTodoTeamInputData inputDataFail = new DeleteTodoTeamInputData(10, "testTeam210398129382103989081");
            createUserInteractor.execute(inputDataFail);

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
    private static DeleteTodoTeamInteractor getDeleteTodoTeamInteractor(DataAccess dataAccess) {
        DeleteTodoTeamOutputBoundary presenter = new DeleteTodoTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteTodoTeamOutputData createUserOutputData) {
                assertEquals("Successfully Deleted Todo From Team", createUserOutputData.getMessage());
                System.out.println("Success: " + createUserOutputData.getMessage());
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Failed to Delete Todo From List", error);
                System.out.println("Failure: " + error);
            }
        };

        return new DeleteTodoTeamInteractor(dataAccess, presenter);
    }
}