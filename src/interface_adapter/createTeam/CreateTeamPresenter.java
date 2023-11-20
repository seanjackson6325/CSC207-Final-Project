package interface_adapter.createTeam;

import use_case.CreateTeam.CreateTeamOutputBoundary;
import use_case.CreateTeam.CreateTeamOutputData;
import use_case.DeleteTodo.DeleteTodoOutputData;

public class CreateTeamPresenter implements CreateTeamOutputBoundary {

    public CreateTeamPresenter() {
        //TODO
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
