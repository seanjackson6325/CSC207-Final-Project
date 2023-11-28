package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Team;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.RemoveMember.RemoveMemberInputData;
import use_case.RemoveMember.RemoveMemberInteractor;
import use_case.RemoveMember.RemoveMemberOutputBoundary;
import use_case.RemoveMember.RemoveMemberOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RemoveMemberInteractorTest {
    @Test
    public void testExecute() {
        DataAccess dataAccess = new DataAccess();
        try {
            Thread.sleep(2000);
            dataAccess.deleteTeam("testTeam210398129382103989081");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser1029837489128392");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser1029837489128393");
        } catch (Exception ignored) {

        }

        try {
            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            testuser.getTeam().add("testTeam210398129382103989081");
            testuser.setTeams(testuser.getTeam());
            User testuser2 = new User("testUser1029837489128392", "1234", todoList, teamList);
            testuser2.getTeam().add("testTeam210398129382103989081");
            testuser2.setTeams(testuser2.getTeam());
            List<String> managerList = new ArrayList<>();
            List<String> memberList = new ArrayList<>();
            Team testTeam = new Team("testTeam210398129382103989081", todoList, managerList, memberList);
            testTeam.getMembers().add(testuser.getUsername());
            testTeam.getMembers().add(testuser2.getUsername());
            testTeam.setMembers(testTeam.getMembers());
            testTeam.getManagers().add("testUser102983748912839");
            testTeam.setManagers(testTeam.getManagers());
            dataAccess.createUser(testuser);
            dataAccess.createUser(testuser2);
            dataAccess.createTeam(testTeam);
            EntityMemory.setLoggedInUser(testuser);

            RemoveMemberInteractor removeMemberInteractor = getRemoveMemberInteractor(testuser2, dataAccess);

            // assert that the interactor is properly initialized
            assertNotNull(removeMemberInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            RemoveMemberInputData inputData = new RemoveMemberInputData("testUser1029837489128392", "testTeam210398129382103989081");
            removeMemberInteractor.execute(inputData);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            RemoveMemberInputData inputDataNotOnTeam = new RemoveMemberInputData("testUser1029837489128392", "testTeam210398129382103989081");
            removeMemberInteractor.execute(inputDataNotOnTeam);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            RemoveMemberInputData inputDataExists = new RemoveMemberInputData("testUser1029837489128393", "testTeam210398129382103989081");
            removeMemberInteractor.execute(inputDataExists);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            RemoveMemberInputData inputDataManager = new RemoveMemberInputData("testUser102983748912839", "testTeam210398129382103989081");
            removeMemberInteractor.execute(inputDataManager);

            Thread.sleep(2000);
            dataAccess.deleteTeam("testTeam210398129382103989081");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser1029837489128392");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser1029837489128393");
        }
        catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


    }

    @NotNull
    private static RemoveMemberInteractor getRemoveMemberInteractor(User user, DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        final boolean[] failure3 = {false};
        RemoveMemberOutputBoundary presenter = new RemoveMemberOutputBoundary() {
            @Override
            public void successView(RemoveMemberOutputData removeMemberOutputData) {
                assertEquals(removeMemberOutputData.getMessage(), "User successfully removed " + user.getUsername() + " from the team!");
                System.out.println("Success: " + removeMemberOutputData.getMessage());
            }

            @Override
            public void failureView(RemoveMemberOutputData removeMemberOutputData) {

                if (!failure1[0]) {
                    assertEquals("User is not on the team!", removeMemberOutputData.getMessage());
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals("User does not exist!", removeMemberOutputData.getMessage());
                    failure2[0] = true;
                } else if (!failure3[0]) {
                    assertEquals("Can't remove manager!", removeMemberOutputData.getMessage());
                    failure3[0] = true;
                }
                System.out.println("Failure: " + removeMemberOutputData.getMessage());
            }
        };

        return new RemoveMemberInteractor(dataAccess, presenter);
    }


}
