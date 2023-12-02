package use_case.DeleteTodoTeam;

import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;

public class DeleteTodoTeamInteractor implements DeleteTodoTeamInputBoundary{

    private final DeleteTodoTeamOutputBoundary deleteTodoTeamPresenter;

    private final DataAccessInterface dataAccess;

    public DeleteTodoTeamInteractor(DataAccessInterface dataAccess,
                                    DeleteTodoTeamOutputBoundary deleteTodoTeamPresenter)
    {
        this.dataAccess = dataAccess;
        this.deleteTodoTeamPresenter = deleteTodoTeamPresenter;
    }


    @Override
    public void execute(DeleteTodoTeamInputData input) {
        try
        {
            Team team = dataAccess.readTeam(input.getTeamName());
            team.getTeamTasks().remove(input.getIndex());
            dataAccess.updateTeam(team);

            deleteTodoTeamPresenter.prepareSuccessView(new DeleteTodoTeamOutputData("Successfully Deleted Todo From Team"));
        }
        catch (Exception e)
        {
            deleteTodoTeamPresenter.prepareFailView("Failed to Delete Todo From List");
        }
    }
}
