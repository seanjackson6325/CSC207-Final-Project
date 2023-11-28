package use_case.AddMember;

import Factory.TodoFactory;
import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;
import entity.Todo;
import entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class AddMemberInteractor implements AddMemberInputBoundary {
    final DataAccessInterface memberDataAccessObject;
    final AddMemberOutputBoundary memberPresenter;
    public AddMemberInteractor(DataAccessInterface memberDataAccessInterface,
                               AddMemberOutputBoundary memberOutputBoundary) {
        this.memberDataAccessObject = memberDataAccessInterface;
        this.memberPresenter = memberOutputBoundary;
    }

    @Override
    public void execute(AddMemberInputData inputData) {
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
        if (caughtMember) {
            AddMemberOutputData outputData = new AddMemberOutputData("User already on the team!");
            memberPresenter.failureView(outputData);
        } else if (memberDataAccessObject.readUser(username) == null) {
            AddMemberOutputData outputData = new AddMemberOutputData("User does not exist!");
            memberPresenter.failureView(outputData);
        } else {
            team.getMembers().add(username);
            team.setMembers(team.getMembers());
            user.getTeam().add(team.getTeamName());
            user.setTeams(user.getTeam());
            memberDataAccessObject.updateUser(user);
            memberDataAccessObject.updateTeam(team);
            AddMemberOutputData outputData = new AddMemberOutputData("User successfully added onto the team!");
            memberPresenter.successView(outputData);
        }
    }
}