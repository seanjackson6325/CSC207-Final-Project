package interface_adapter.addMember;

import interface_adapter.createTeam.TeamViewModel;
import use_case.AddMember.AddMemberOutputBoundary;
import use_case.AddMember.AddMemberOutputData;
import view.ViewManager;

import javax.swing.*;

public class AddMemberPresenter implements AddMemberOutputBoundary {

    TeamViewModel teamViewModel;
    ViewManager viewManager;

    public AddMemberPresenter(ViewManager viewManager, TeamViewModel teamViewModel)
    {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void successView(AddMemberOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
        teamViewModel.getTeamState().setIsAddTeamMemberFailed(false);
    }

    @Override
    public void failureView(AddMemberOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
        teamViewModel.getTeamState().setIsAddTeamMemberFailed(true);
    }
}
