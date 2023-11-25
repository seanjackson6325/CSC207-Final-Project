package use_case.DeleteTodo;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Team;
import entity.Todo;
import entity.User;
import org.junit.Test;
import use_case.CreateTeam.CreateTeamInputData;
import use_case.CreateTeam.CreateTeamInteractor;
import use_case.CreateTeam.CreateTeamOutputBoundary;
import use_case.CreateTeam.CreateTeamOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeleteTodoInteractorTest {

    @Test
    public void testExecute() {

        try {
            // Initialize the interactor with the test cases at the end of the presenter
            DeleteTodoOutputBoundary presenter = new DeleteTodoOutputBoundary() {
                @Override
                public void prepareSuccessView(DeleteTodoOutputData deleteTodoOutputData) {
                    assert(Objects.equals(deleteTodoOutputData.getMessage(), "Successfully Deleted Todo From List"));
                }

                @Override
                public void prepareFailView(String error) {
                    assert(Objects.equals(error, "Failed to Delete Todo From List"));
                }
            };

            DataAccess dataAccess = new DataAccess();
            DeleteTodoInteractor deleteTodoInteractor = new DeleteTodoInteractor(dataAccess, presenter);

            List<Todo> todoList = new ArrayList<>();
            todoList.add(
                    new Todo(
                            "testtodo1",
                            "this is a test todo 1",
                            LocalDateTime.now(),
                            LocalDateTime.now().plusDays(1),
                            "testuser1_specialcase_1956129837469872134",
                            "testuser2_specialcase_1956129837469872134",
                            false
                    ));
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            dataAccess.createUser(testuser);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(deleteTodoInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            DeleteTodoInputData inputData = new DeleteTodoInputData(0, EntityMemory.getLoggedInUser().getTaskList(), null);
            deleteTodoInteractor.execute(inputData);

            // call execute again to get the fail view
            Thread.sleep(2000);
            deleteTodoInteractor.execute(inputData);

            // add back the to-do to test for removing for team.
            todoList.add(
                    new Todo(
                            "testtodo1",
                            "this is a test todo 1",
                            LocalDateTime.now(),
                            LocalDateTime.now().plusDays(1),
                            "testuser1_specialcase_1956129837469872134",
                            "testuser2_specialcase_1956129837469872134",
                            false
                    ));
            Thread.sleep(2000);
            Team testTeam = new Team("TestTeam950871023894776", todoList, new ArrayList<>(), new ArrayList<>());
            dataAccess.createTeam(testTeam);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            DeleteTodoInputData inputDataTeam = new DeleteTodoInputData(0, testTeam.getTeamTasks(), testTeam);
            deleteTodoInteractor.execute(inputDataTeam);

            // call execute again to get the fail view
            Thread.sleep(2000);
            deleteTodoInteractor.execute(inputDataTeam);

            // delete the user/team used
            Thread.sleep(2000);
            dataAccess.deleteTeam("TestTeam950871023894776");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }
    }
}
