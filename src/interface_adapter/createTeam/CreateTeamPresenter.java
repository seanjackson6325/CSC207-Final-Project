package interface_adapter.createTeam;

import interface_adapter.user.UserViewModel;
import use_case.CreateTeam.CreateTeamOutputBoundary;
import use_case.CreateTeam.CreateTeamOutputData;
import use_case.DeleteTodo.DeleteTodoOutputData;
import view.ViewManager;

public class CreateTeamPresenter implements CreateTeamOutputBoundary {

    ViewManager viewManager;
    TeamViewModel teamViewModel;

    public CreateTeamPresenter(ViewManager viewManager, TeamViewModel teamViewModel) {
        this.viewManager = viewManager;
        this.teamViewModel = teamViewModel;
    }

    @Override
    public void prepareSuccessView(CreateTeamOutputData createTeamOutputData) {
        // this gets the success message, createTeamOutputData.getMessage();
        // probably want reload/update the view we are in

        //TODO
    }

    @Override
    public void prepareFailView(String error) {
        //TODO

    }
}
