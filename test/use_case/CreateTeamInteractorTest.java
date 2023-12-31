package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Todo;
import entity.User;
import org.junit.Test;
import use_case.CreateTeam.CreateTeamInputData;
import use_case.CreateTeam.CreateTeamInteractor;
import use_case.CreateTeam.CreateTeamOutputBoundary;
import use_case.CreateTeam.CreateTeamOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateTeamInteractorTest {

    @Test
    public void testExecute() {

        try {
            // Initialize the interactor with the test cases at the end of the presenter
            CreateTeamOutputBoundary presenter = new CreateTeamOutputBoundary() {
                @Override
                public void prepareSuccessView(CreateTeamOutputData createTeamOutputData) {
                    assertEquals(createTeamOutputData.getMessage(), "Team Created");
                    System.out.println("success1");
                }

                @Override
                public void prepareFailView(String error) {
                    assert(Objects.equals(error, "Team Name Already Exists")
                            | Objects.equals(error, "Failed to Create Team"));
                }
            };

            DataAccess dataAccess = new DataAccess();
            CreateTeamInteractor createTeamInteractor = new CreateTeamInteractor(dataAccess, presenter);

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


            // returns a runtime error
            EntityMemory.setLoggedInUser(null);
            createTeamInteractor.execute(inputData);

        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }
    }
}
