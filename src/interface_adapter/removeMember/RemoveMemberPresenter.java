package interface_adapter.removeMember;

import interface_adapter.createTeam.TeamViewModel;
import use_case.RemoveMember.RemoveMemberOutputBoundary;
import use_case.RemoveMember.RemoveMemberOutputData;
import view.ViewManager;

import javax.swing.*;

public class RemoveMemberPresenter implements RemoveMemberOutputBoundary {

    TeamViewModel teamViewModel;
    ViewManager viewManager;

    public RemoveMemberPresenter(ViewManager viewManager, TeamViewModel teamViewModel)
    {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }
    @Override
    public void successView(RemoveMemberOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
    }

    @Override
    public void failureView(RemoveMemberOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
    }
}
