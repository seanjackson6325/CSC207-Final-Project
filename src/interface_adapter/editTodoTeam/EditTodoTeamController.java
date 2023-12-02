package interface_adapter.editTodoTeam;

import use_case.EditTodoTeam.EditTodoTeamInputBoundary;
import use_case.EditTodoTeam.EditTodoTeamInputData;
import use_case.EditTodoTeam.EditTodoTeamOutputBoundary;

import java.time.LocalDateTime;

public class EditTodoTeamController {

    final EditTodoTeamInputBoundary editTodoTeamInteractor;

    public EditTodoTeamController(EditTodoTeamInputBoundary editTodoTeamInteractor)
    {
        this.editTodoTeamInteractor = editTodoTeamInteractor;
    }

    public void execute(int index, String name, String desc, LocalDateTime start, LocalDateTime end, String requester, String requestedTo, Boolean status, String team)
    {
        EditTodoTeamInputData input = new EditTodoTeamInputData(
                index,
                name,
                desc,
                start,
                end,
                requester,
                requestedTo,
                status,
                team
        );
        editTodoTeamInteractor.execute(input);
    }

}
