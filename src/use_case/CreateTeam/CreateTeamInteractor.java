package use_case.CreateTeam;

import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;
import entity.User;
import use_case.DeleteTodo.DeleteTodoInputData;
import use_case.DeleteTodo.DeleteTodoOutputBoundary;
import use_case.DeleteTodo.DeleteTodoOutputData;

public class CreateTeamInteractor implements CreateTeamInputBoundary {

    private final CreateTeamOutputBoundary createTeamPresenter;
    private final DataAccessInterface dataAccess;

    public CreateTeamInteractor(DataAccessInterface dataAccess,
                                CreateTeamOutputBoundary createTeamPresenter) {
        this.dataAccess = dataAccess;
        this.createTeamPresenter = createTeamPresenter;
    }

    @Override
    public void execute(CreateTeamInputData createTeamInputData) {

        try {
            if (dataAccess.readTeam(createTeamInputData.getTeamName()) == null) {
                // Create Team
                Team newTeam = new Team(createTeamInputData.getTeamName(), null, null, null);
                // Add team in the user's team list
                User user = EntityMemory.getLoggedInUser();
                user.getTeam().add(newTeam.getTeamName());

                // update the change in the Database (for both User and newTeam)
                dataAccess.updateUser(user);
                dataAccess.createTeam(newTeam);

                createTeamPresenter.prepareSuccessView(new CreateTeamOutputData("Team Created"));
            }
            else {
                createTeamPresenter.prepareFailView("Team Name Already Exists");
            }

        }
        catch (Exception e) {
            createTeamPresenter.prepareFailView("Failed to Create Team");
        }
    }
}
