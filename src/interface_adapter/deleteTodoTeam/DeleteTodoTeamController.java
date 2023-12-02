package interface_adapter.deleteTodoTeam;

import entity.Team;
import entity.Todo;
import interface_adapter.deleteTodo.DeleteTodoController;
import use_case.DeleteTodoTeam.DeleteTodoTeamInputBoundary;
import use_case.DeleteTodoTeam.DeleteTodoTeamInputData;
import use_case.DeleteTodoTeam.DeleteTodoTeamInteractor;

import java.util.List;

public class DeleteTodoTeamController {

    final DeleteTodoTeamInputBoundary deleteTodoTeamInteractor;

    public DeleteTodoTeamController(DeleteTodoTeamInputBoundary deleteTodoTeamInteractor)
    {
        this.deleteTodoTeamInteractor = deleteTodoTeamInteractor;
    }

    public void execute(int index, String team)
    {
        DeleteTodoTeamInputData input = new DeleteTodoTeamInputData(index, team);
        deleteTodoTeamInteractor.execute(input);
    }

}
