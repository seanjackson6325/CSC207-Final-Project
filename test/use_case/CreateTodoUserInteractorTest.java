package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.CreateTodoUser.CreateTodoUserInputData;
import use_case.CreateTodoUser.CreateTodoUserInteractor;
import use_case.CreateTodoUser.CreateTodoUserOutputBoundary;
import use_case.CreateTodoUser.CreateTodoUserOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateTodoUserInteractorTest {
    @Test
    public void testExecute() {

        DataAccess dataAccess = new DataAccess();
        try {
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        } catch (Exception ignored) {

        }

        try {
            CreateTodoUserInteractor createUserInteractor = getCreateTodoUserInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            dataAccess.createUser(testuser);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(createUserInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            CreateTodoUserInputData inputData = new CreateTodoUserInputData("TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", true, "testUser102983748912839");
            createUserInteractor.execute(inputData);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoUserInputData inputDataExists = new CreateTodoUserInputData("TestTodo1", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", true, "testUser102983748912839");
            createUserInteractor.execute(inputDataExists);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoUserInputData inputDataNoName = new CreateTodoUserInputData("", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", true, "testUser102983748912839");
            createUserInteractor.execute(inputDataNoName);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoUserInputData inputDataDates = new CreateTodoUserInputData("TestTodo2", "TestDesc", LocalDateTime.now().plusDays(6), LocalDateTime.now().plusDays(5), "testUser102983748912839", true, "testUser102983748912839");
            createUserInteractor.execute(inputDataDates);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            CreateTodoUserInputData inputDateStartDate = new CreateTodoUserInputData("TestTodo3", "TestDesc", LocalDateTime.now().minusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", true, "testUser102983748912839");
            createUserInteractor.execute(inputDateStartDate);

            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


    }

    @NotNull
    private static CreateTodoUserInteractor getCreateTodoUserInteractor(DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        final boolean[] failure4 = {false};
        CreateTodoUserOutputBoundary presenter = new CreateTodoUserOutputBoundary() {
            @Override
            public void createSuccessView(CreateTodoUserOutputData createUserOutputData) {
                assertEquals(createUserOutputData.getMessage(), "Task Successfully Created!");
                System.out.println("Success: " + createUserOutputData.getMessage());
            }

            @Override
            public void createFailureView(CreateTodoUserOutputData createUserOutputData) {

                if (!failure1[0]) {
                    assertEquals(createUserOutputData.getMessage(), "There is another task with the same name!");
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals(createUserOutputData.getMessage(), "Task does not have a name!");
                    failure2[0] = true;
                } else if (!failure3[0]) {
                    assertEquals(createUserOutputData.getMessage(), "Invalid start & end dates!");
                    failure3[0] = true;
                } else if (!failure4[0]) {
                    assertEquals(createUserOutputData.getMessage(), "Invalid start date!");
                    failure4[0] = true;
                }
                System.out.println("Failure: " + createUserOutputData.getMessage());
            }
        };

        return new CreateTodoUserInteractor(dataAccess, presenter);
    }
}
