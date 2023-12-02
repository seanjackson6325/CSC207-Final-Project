package interface_adapter.deleteTodoTeam;

import interface_adapter.createTeam.TeamViewModel;
import use_case.DeleteTodoTeam.DeleteTodoTeamOutputBoundary;
import use_case.DeleteTodoTeam.DeleteTodoTeamOutputData;
import view.ViewManager;

import javax.swing.*;

public class DeleteTodoTeamPresenter implements DeleteTodoTeamOutputBoundary {

    private ViewManager viewManager;
    private TeamViewModel teamViewModel;

    public DeleteTodoTeamPresenter(ViewManager viewManager, TeamViewModel teamViewModel) {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteTodoTeamOutputData output) {
        JOptionPane.showMessageDialog(null, output.getMessage());
    }

    @Override
    public void prepareFailView(String error) {
        JOptionPane.showMessageDialog(null, error);
    }
}
