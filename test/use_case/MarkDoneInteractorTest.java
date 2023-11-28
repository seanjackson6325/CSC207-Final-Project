package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.MarkDone.MarkDoneInputData;
import use_case.MarkDone.MarkDoneInteractor;
import use_case.MarkDone.MarkDoneOutputBoundary;
import use_case.MarkDone.MarkDoneOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MarkDoneInteractorTest {
    @Test
    public void testExecute() {

        DataAccess dataAccess = new DataAccess();
        try {
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        } catch (Exception ignored) {

        }

        try {
            MarkDoneInteractor createUserInteractor = getMarkDoneInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>(List.of(new Todo("TestTodo", "TestDesc", LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "testUser102983748912839", "testUser102983748912839", false)));
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            dataAccess.createUser(testuser);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(createUserInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            MarkDoneInputData inputData = new MarkDoneInputData("TestTodo");
            createUserInteractor.execute(inputData);

            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


    }

    @NotNull
    private static MarkDoneInteractor getMarkDoneInteractor(DataAccess dataAccess) {
        MarkDoneOutputBoundary presenter = new MarkDoneOutputBoundary() {
            @Override
            public void successView(MarkDoneOutputData createUserOutputData) {
                assertEquals(createUserOutputData.getMessage(), "Task Completed!");
                System.out.println("Success: " + createUserOutputData.getMessage());
            }
        };

        return new MarkDoneInteractor(dataAccess, presenter);
    }
}