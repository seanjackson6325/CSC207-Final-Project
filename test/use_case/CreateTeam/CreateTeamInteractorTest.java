package use_case.CreateTeam;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateTeamInteractorTest {
    @Test
    public void testExecute() {

        DataAccess dataAccess = new DataAccess();
        try {
            Thread.sleep(2000);
            dataAccess.deleteTeam("TestTeam950871023894776");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
        } catch (Exception ignored) {

        }

        try {
            CreateTeamInteractor createTeamInteractor = getCreateTeamInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            dataAccess.createUser(testuser);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(createTeamInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            CreateTeamInputData inputData = new CreateTeamInputData("TestTeam950871023894776");
            createTeamInteractor.execute(inputData);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            createTeamInteractor.execute(inputData);

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

    @NotNull
    private static CreateTeamInteractor getCreateTeamInteractor(DataAccess dataAccess) {
        CreateTeamOutputBoundary presenter = new CreateTeamOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateTeamOutputData createTeamOutputData) {
                assertEquals(createTeamOutputData.getMessage(), "Team Created");
                System.out.println("Success: " + createTeamOutputData.getMessage());
            }

            @Override
            public void prepareFailView(String error) {
                assert(Objects.equals(error, "Team Name Already Exists"));
                System.out.println("Failure: " + error);
            }
        };

        return new CreateTeamInteractor(dataAccess, presenter);
    }


}
