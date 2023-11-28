package use_case;

import app.EntityMemory;
import data_access.DataAccess;
import entity.Team;
import entity.Todo;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import use_case.AddMember.AddMemberInputData;
import use_case.AddMember.AddMemberInteractor;
import use_case.AddMember.AddMemberOutputBoundary;
import use_case.AddMember.AddMemberOutputData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddMemberInteractorTest {
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
        } catch (Exception ignored) {

        }

        try {
            AddMemberInteractor addMemberInteractor = getAddMemberInteractor(dataAccess);

            List<Todo> todoList = new ArrayList<>();
            List<String> teamList = new ArrayList<>();
            User testuser = new User("testUser102983748912839", "1234", todoList, teamList);
            testuser.getTeam().add("testTeam210398129382103989081");
            testuser.setTeams(testuser.getTeam());
            User testuser2 = new User("testUser1029837489128392", "1234", todoList, teamList);
            List<String> managerList = new ArrayList<>();
            List<String> memberList = new ArrayList<>();
            Team testTeam = new Team("testTeam210398129382103989081", todoList, managerList, memberList);
            testTeam.getMembers().add(testuser.getUsername());
            testTeam.setMembers(testTeam.getMembers());
            dataAccess.createUser(testuser);
            dataAccess.createUser(testuser2);
            dataAccess.createTeam(testTeam);
            EntityMemory.setLoggedInUser(testuser);

            // assert that the interactor is properly initialized
            assertNotNull(addMemberInteractor);

            // call execute (sleep to avoid errors with DA)
            Thread.sleep(2000);
            AddMemberInputData inputData = new AddMemberInputData("testUser1029837489128392", "testTeam210398129382103989081");
            addMemberInteractor.execute(inputData);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            AddMemberInputData inputDataOnTeam = new AddMemberInputData("testUser1029837489128392", "testTeam210398129382103989081");
            addMemberInteractor.execute(inputDataOnTeam);

            // call again to return an error after waiting a little bit (to avoid error with dataAccess)
            Thread.sleep(2000);
            AddMemberInputData inputDataExists = new AddMemberInputData("testUser1029837489128393", "testTeam210398129382103989081");
            addMemberInteractor.execute(inputDataExists);

            Thread.sleep(2000);
            dataAccess.deleteTeam("testTeam210398129382103989081");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser102983748912839");
            Thread.sleep(2000);
            dataAccess.deleteUser("testUser1029837489128392");
        }
            catch (InterruptedException ignored) {
            assert false;
            System.out.println("Rerun the test");
        }


}

    @NotNull
    private static AddMemberInteractor getAddMemberInteractor(DataAccess dataAccess) {
        final boolean[] failure1 = {false};
        final boolean[] failure2 = {false};
        AddMemberOutputBoundary presenter = new AddMemberOutputBoundary() {
            @Override
            public void successView(AddMemberOutputData addMemberOutputData) {
                assertEquals(addMemberOutputData.getMessage(), "User successfully added onto the team!");
                System.out.println("Success: " + addMemberOutputData.getMessage());
            }

            @Override
            public void failureView(AddMemberOutputData addMemberOutputData) {

                if (!failure1[0]) {
                    assertEquals(addMemberOutputData.getMessage(), "User already on the team!");
                    failure1[0] = true;
                } else if (!failure2[0]) {
                    assertEquals(addMemberOutputData.getMessage(), "User does not exist!");
                    failure2[0] = true;
                }
                System.out.println("Failure: " + addMemberOutputData.getMessage());
            }
        };

        return new AddMemberInteractor(dataAccess, presenter);
    }


}
