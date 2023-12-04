package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Team;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.CompleteTodoTeam.CompleteTodoTeamInputData;
import use_case.EditTodoTeam.EditTodoTeamInputData;
import use_case.EditTodoTeam.EditTodoTeamInteractor;
import use_case.EditTodoTeam.EditTodoTeamOutputBoundary;
import use_case.EditTodoTeam.EditTodoTeamOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EditTodoTeamInteractorTest {
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
            EditTodoTeamInteractor editTodoInteractor = getEditTodoTeamInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>(List.of(new Todo("TestTodo", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false), new Todo("TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false)));
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
            assertNotNull(editTodoInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            EditTodoTeamInputData inputData = new EditTodoTeamInputData(1, "TestTodo", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false, "testTeam210398129382103989081");
            editTodoInteractor.execute(inputData);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            EditTodoTeamInputData inputDataNoName = new EditTodoTeamInputData(1, "", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false, "testTeam210398129382103989081");
            editTodoInteractor.execute(inputDataNoName);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            EditTodoTeamInputData inputDataDates = new EditTodoTeamInputData(1, "TestTodo", "TestDesc", LocalDateTime.now().plusDays(6), LocalDateTime.now().plusDays(5), "testUser102983748912839", "testUser102983748912839", false, "testTeam210398129382103989081");
            editTodoInteractor.execute(inputDataDates);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            EditTodoTeamInputData inputDataStartDate = new EditTodoTeamInputData(1, "TestTodo", "TestDesc", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false, "testTeam210398129382103989081");
            editTodoInteractor.execute(inputDataStartDate);

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
    private static EditTodoTeamInteractor getEditTodoTeamInteractor(DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        EditTodoTeamOutputBoundary presenter = new EditTodoTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(EditTodoTeamOutputData editTodoOutputData) {
                assertEquals(editTodoOutputData.getMessage(), "Task Successfully Edited!");
                System.out.println("Success: " + editTodoOutputData.getMessage());
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

        return new EditTodoTeamInteractor(dataAccess, presenter);
    }
}