package use_case.RemoveMember;

import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;
import entity.User;

import java.util.List;

public class RemoveMemberInteractor implements RemoveMemberInputBoundary {
    final DataAccessInterface memberDataAccessObject;
    final RemoveMemberOutputBoundary memberPresenter;
    public RemoveMemberInteractor(DataAccessInterface memberDataAccessInterface,
                                  RemoveMemberOutputBoundary memberOutputBoundary) {
        this.memberDataAccessObject = memberDataAccessInterface;
        this.memberPresenter = memberOutputBoundary;
    }

    @Override
    public void execute(RemoveMemberInputData inputData) {
        String username = inputData.getUser();
        User user = memberDataAccessObject.readUser(username);
        Team team = memberDataAccessObject.readTeam(inputData.getTeam());

        boolean caughtMember = false;
        List<String> members = team.getMembers();
        for (String member: members) {
            if (member.equals(username)) {
                caughtMember = true;
                break;
            }
        }

        boolean caughtManager = false;
        List<String> managers = team.getManagers();
        for (String manager: managers) {
            if (manager.equals(username)) {
                caughtManager = true;
                break;
            }
        }

        if (memberDataAccessObject.readUser(username) == null) {
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("User does not exist!");
            memberPresenter.failureView(outputData);
        } else if (!caughtMember) {
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("User is not on the team!");
            memberPresenter.failureView(outputData);
        } else if (caughtManager) {
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("Can't remove manager!");
            memberPresenter.failureView(outputData);
        } else {
            team.getMembers().remove(username);
            team.setMembers(team.getMembers());
            user.getTeam().remove(team.getTeamName());
            user.setTeams(user.getTeam());
            memberDataAccessObject.updateUser(user);
            memberDataAccessObject.updateTeam(team);
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("User successfully removed " + username + " from the team!");
            memberPresenter.successView(outputData);
        }
    }
}