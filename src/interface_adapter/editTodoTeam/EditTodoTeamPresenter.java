package interface_adapter.editTodoTeam;

import interface_adapter.createTeam.TeamViewModel;
import use_case.EditTodoTeam.EditTodoTeamOutputBoundary;
import use_case.EditTodoTeam.EditTodoTeamOutputData;
import view.ViewManager;

import javax.swing.*;

public class EditTodoTeamPresenter implements EditTodoTeamOutputBoundary {

    private ViewManager viewManager;
    private TeamViewModel teamViewModel;

    public EditTodoTeamPresenter(ViewManager viewManager,
                                 TeamViewModel teamViewModel)
    {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void prepareSuccessView(EditTodoTeamOutputData output) {
        teamViewModel.getTeamState().setIsEditTodoFailed(false);
        JOptionPane.showMessageDialog(null, output.getMessage());
    }

    @Override
    public void prepareFailView(String error) {
        teamViewModel.getTeamState().setIsEditTodoFailed(true);
        JOptionPane.showMessageDialog(null, error);
    }
}
