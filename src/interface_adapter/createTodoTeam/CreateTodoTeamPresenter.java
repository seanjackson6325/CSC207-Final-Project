package interface_adapter.createTodoTeam;

import interface_adapter.createTeam.TeamViewModel;
import use_case.CreateTodoTeam.CreateTodoTeamOutputBoundary;
import use_case.CreateTodoTeam.CreateTodoTeamOutputData;
import view.ViewManager;

import javax.swing.*;

public class CreateTodoTeamPresenter implements CreateTodoTeamOutputBoundary {

    TeamViewModel teamViewModel;
    ViewManager viewManager;

    public CreateTodoTeamPresenter(ViewManager viewManager, TeamViewModel teamViewModel)
    {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void successView(CreateTodoTeamOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
        teamViewModel.getTeamState().setIsAddTodoFailed(false);
    }

    @Override
    public void failureView(CreateTodoTeamOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
        teamViewModel.getTeamState().setIsAddTodoFailed(true);
    }
}
