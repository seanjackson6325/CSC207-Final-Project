package interface_adapter.createTeam;

import interface_adapter.user.UserViewModel;
import use_case.CreateTeam.CreateTeamOutputBoundary;
import use_case.CreateTeam.CreateTeamOutputData;
import use_case.DeleteTodo.DeleteTodoOutputData;
import view.ViewManager;

import javax.swing.*;

public class CreateTeamPresenter implements CreateTeamOutputBoundary {

    ViewManager viewManager;
    TeamViewModel teamViewModel;

    public CreateTeamPresenter(ViewManager viewManager, TeamViewModel teamViewModel) {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void prepareSuccessView(CreateTeamOutputData createTeamOutputData) {
        JOptionPane.showMessageDialog(null, createTeamOutputData.getMessage());
        teamViewModel.getTeamState().setIsCreateTeamFailed(false);
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
        teamViewModel.getTeamState().setIsCreateTeamFailed(true);
    }
}
