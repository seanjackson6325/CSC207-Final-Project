package interface_adapter.completeTodoTeam;

import interface_adapter.createTeam.TeamViewModel;
import use_case.CompleteTodoTeam.CompleteTodoTeamOutputBoundary;
import use_case.CompleteTodoTeam.CompleteTodoTeamOutputData;
import view.ViewManager;

import javax.swing.*;

public class CompleteTodoTeamPresenter implements CompleteTodoTeamOutputBoundary {

    ViewManager viewManager;
    TeamViewModel teamViewModel;

    public CompleteTodoTeamPresenter(ViewManager viewManager, TeamViewModel teamViewModel) {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void prepareSuccessView(CompleteTodoTeamOutputData output) {
        JOptionPane.showMessageDialog(null, output.getMessage());
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
    }

}
