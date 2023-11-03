package use_case.CreateTodoTeam;

public class CreateTodoTeamInteractor implements CreateTodoTeamInputBoundary {
    final CreateTodoTeamDataAccessInterface teamDataAccessObject;
    final CreateTodoTeamOutputBoundary teamPresenter;
    public CreateTodoTeamInteractor(CreateTodoTeamDataAccessInterface teamDataAccessInterface,
                                    CreateTodoTeamOutputBoundary teamOutputBoundary) {
        this.teamDataAccessObject = teamDataAccessInterface;
        this.teamPresenter = teamOutputBoundary;
    }

    @Override
    public void execute(CreateTodoTeamInputData inputData) {

    }
}