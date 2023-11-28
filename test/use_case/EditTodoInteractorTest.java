package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.CreateTodoUser.CreateTodoUserInputData;
import use_case.EditTodo.EditTodoInputData;
import use_case.EditTodo.EditTodoInteractor;
import use_case.EditTodo.EditTodoOutputBoundary;
import use_case.EditTodo.EditTodoOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EditTodoInteractorTest {
    @Test
    public void testExecute() {

        DataAccess dataAccess = new DataAccess();
        try {
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        } catch (Exception ignored) {

        }

        try {
            EditTodoInteractor editTodoInteractor = getEditTodoInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>(List.of(new Todo("TestTodo", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false), new Todo("TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false)));
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            dataAccess.createUser(testuser);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(editTodoInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            EditTodoInputData inputData = new EditTodoInputData("TestTodo", "TestTodoNewName", "TestDescNewDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", false, "testUser102983748912839");
            editTodoInteractor.execute(inputData);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            EditTodoInputData inputDataExists = new EditTodoInputData("TestTodo", "TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", false, "testUser102983748912839");
            editTodoInteractor.execute(inputDataExists);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            EditTodoInputData inputDataNoName = new EditTodoInputData("TestTodo","", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", false, "testUser102983748912839");
            editTodoInteractor.execute(inputDataNoName);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            EditTodoInputData inputDataDates = new EditTodoInputData("TestTodo", "TestTodo", "TestDesc", LocalDateTime.now().plusDays(6), LocalDateTime.now().plusDays(5), "testUser102983748912839", false, "testUser102983748912839");
            editTodoInteractor.execute(inputDataDates);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            EditTodoInputData inputDateStartDate = new EditTodoInputData("TestTodo", "TestTodo", "TestDesc", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", false, "testUser102983748912839");
            editTodoInteractor.execute(inputDateStartDate);

            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


    }

    @NotNull
    private static EditTodoInteractor getEditTodoInteractor(DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        final boolean[] failure4 = {false};
        EditTodoOutputBoundary presenter = new EditTodoOutputBoundary() {
            @Override
            public void editSuccessView(EditTodoOutputData editTodoOutputData) {
                assertEquals(editTodoOutputData.getMessage(), "Task Successfully Edited!");
                System.out.println("Success: " + editTodoOutputData.getMessage());
            }

            @Override
            public void editFailureView(EditTodoOutputData editTodoOutputData) {
                if (!failure1[0]) {
                    assertEquals(editTodoOutputData.getMessage(), "There is another task with the same name!");
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals(editTodoOutputData.getMessage(), "Task does not have a name!");
                    failure2[0] = true;
                } else if (!failure3[0]) {
                    assertEquals(editTodoOutputData.getMessage(), "Invalid start & end dates!");
                    failure3[0] = true;
                } else if (!failure4[0]) {
                    assertEquals(editTodoOutputData.getMessage(), "Invalid start date!");
                    failure4[0] = true;
                }
                System.out.println("Failure: " + editTodoOutputData.getMessage());
            }
        };

        return new EditTodoInteractor(dataAccess, presenter);
    }
}