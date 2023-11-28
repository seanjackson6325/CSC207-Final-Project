package use_case;

import Factory.UserFactory;
import app.EntityMemory;
import data_access.DataAccess;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.Signup.SignupInputData;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupOutputBoundary;
import use_case.Signup.SignupOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SignupInteractorTest {
    @Test
    public void testExecute() {

        try {
            DataAccess dataAccess = new DataAccess();
            try {
                Thread.sleep(2000);
                dataAccess.deleteUser("testUser102983748912839");
                Thread.sleep(2000);
                dataAccess.deleteUser("testUser1029837489128391");
            } catch (Exception ignored) {

            }
            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testUser = new User("testUser102983748912839", "1234", todoList, teamList);

            SignupInteractor SignupInteractor = getSignupInteractor(testUser, dataAccess);

            // assert that the interactor is properly initialized
            assertNotNull(SignupInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            SignupInputData inputData = new SignupInputData("testUser102983748912839", "1234", "1234");
            SignupInteractor.execute(inputData);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            SignupInputData inputDataExists = new SignupInputData("testUser102983748912839", "1234", "1234");
            SignupInteractor.execute(inputDataExists);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            SignupInputData inputDataWrongPswrd = new SignupInputData("testUser1029837489128391", "1234", "12345");
            SignupInteractor.execute(inputDataWrongPswrd);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            SignupInputData inputDataBlankName = new SignupInputData("", "1234", "1234");
            SignupInteractor.execute(inputDataBlankName);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            SignupInputData inputDataBlankPswrd = new SignupInputData("testUser1029837489128392", "", "");
            SignupInteractor.execute(inputDataBlankPswrd);

            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser1029837489128391");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


    }

    @NotNull
    private static SignupInteractor getSignupInteractor(User testUser, DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        final boolean[] failure4 = {false};
        SignupOutputBoundary presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData SignupOutputData) {
                assertEquals(SignupOutputData.getUsername(), testUser.getUsername());
                System.out.println("Success");
            }

            @Override
            public void prepareFailView(String error) {

                if (!failure1[0]) {
                    assertEquals("User already exists.", error);
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals("Passwords don't match.", error);
                    failure2[0] = true;
                } else if (!failure3[0]) {
                    assertEquals("Invalid Username!", error);
                    failure3[0] = true;
                } else if (!failure4[0]) {
                    assertEquals("Invalid Password!", error);
                    failure4[0] = true;
                }
                System.out.println("Failure: " + error);
            }
        };
        UserFactory userFactory = new UserFactory();
        return new SignupInteractor(dataAccess, presenter, userFactory);
    }
}
