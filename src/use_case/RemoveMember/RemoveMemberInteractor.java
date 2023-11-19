package use_case.RemoveMember;

import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;

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

        if (!caughtMember) {
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("User is not on the team!");
            memberPresenter.failureView(outputData);
        } else if (memberDataAccessObject.readUser(username) == null) {
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("User does not exist!");
            memberPresenter.failureView(outputData);
        } else if (caughtManager) {
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("Can't remove manager!");
            memberPresenter.failureView(outputData);
        } else {
            team.getMembers().remove(username);
            RemoveMemberOutputData outputData = new RemoveMemberOutputData("User successfully removed " + username + " from the team!");
            memberPresenter.successView(outputData);
        }
    }
}