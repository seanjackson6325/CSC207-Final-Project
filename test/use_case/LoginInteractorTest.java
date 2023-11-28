package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.CreateTodoUser.CreateTodoUserInteractor;
import use_case.Login.LoginInputData;
import use_case.Login.LoginInteractor;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginInteractorTest {
    @Test
    public void testExecute() {

        try {
            DataAccess dataAccess = new DataAccess();
            try {
                Thread.sleep(2000);
                dataAccess.deleteUser("testUser102983748912839");
            } catch (Exception ignored) {

            }
            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testUser = new User("testUser102983748912839", "1234", todoList, teamList);

            LoginInteractor loginInteractor = getLoginInteractor(testUser, dataAccess);

            dataAccess.createUser(testUser);
            EntityMemory.setLoggedInUser(testUser);

            // assert that the interactor is properly initialized
            assertNotNull(loginInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            LoginInputData inputData = new LoginInputData("testUser102983748912839", "1234");
            loginInteractor.execute(inputData);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            LoginInputData inputDataExists = new LoginInputData("testUser1029837489128391HAHAHAHAHHAHAHHAHAHAHAH", "1234");
            loginInteractor.execute(inputDataExists);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            LoginInputData inputDataBlankName = new LoginInputData("", "1234");
            loginInteractor.execute(inputDataBlankName);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            LoginInputData inputDataBlankPswrd = new LoginInputData("testUser102983748912839", "");
            loginInteractor.execute(inputDataBlankPswrd);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            LoginInputData inputDataWrongPswrd = new LoginInputData("testUser102983748912839", "12345");
            loginInteractor.execute(inputDataWrongPswrd);

            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


    }

    @NotNull
    private static LoginInteractor getLoginInteractor(User testUser, DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        final boolean[] failure4 = {false};
        LoginOutputBoundary presenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData loginOutputData) {
                assertEquals(loginOutputData.getUser().getUsername(), testUser.getUsername());
                System.out.println("Success");
            }

            @Override
            public void prepareFailView(String error) {

                if (!failure1[0]) {
                    assertEquals(error, (testUser.getUsername() + "1HAHAHAHAHHAHAHHAHAHAHAH" + ": Account does not exist."));
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals(error, "Invalid Username!");
                    failure2[0] = true;
                } else if (!failure3[0]) {
                    assertEquals(error, "Invalid Password!");
                    failure3[0] = true;
                } else if (!failure4[0]) {
                    assertEquals(error, ("Incorrect password for " + testUser.getUsername() + "."));
                    failure4[0] = true;
                }
                System.out.println("Failure: " + error);
            }
        };

        return new LoginInteractor(dataAccess, presenter);
    }
}
