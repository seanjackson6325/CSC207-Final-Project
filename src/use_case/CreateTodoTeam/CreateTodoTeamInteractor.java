package use_case.CreateTodoTeam;

import data_access.DataAccessInterface;

public class CreateTodoTeamInteractor implements CreateTodoTeamInputBoundary {
    final DataAccessInterface teamDataAccessObject;
    final CreateTodoTeamOutputBoundary teamPresenter;
    public CreateTodoTeamInteractor(DataAccessInterface teamDataAccessInterface,
                                    CreateTodoTeamOutputBoundary teamOutputBoundary) {
        this.teamDataAccessObject = teamDataAccessInterface;
        this.teamPresenter = teamOutputBoundary;
    }

    @Override
    public void execute(CreateTodoTeamInputData inputData) {

    }
}