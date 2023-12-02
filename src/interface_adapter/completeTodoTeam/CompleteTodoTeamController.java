package interface_adapter.completeTodoTeam;

import use_case.CompleteTodoTeam.CompleteTodoTeamInputBoundary;
import use_case.CompleteTodoTeam.CompleteTodoTeamInputData;

import java.time.LocalDateTime;

public class CompleteTodoTeamController {

    public CompleteTodoTeamInputBoundary completeTodoTeamInteractor;

    public CompleteTodoTeamController(CompleteTodoTeamInputBoundary completeTodoTeamInteractor)
    {
        this.completeTodoTeamInteractor = completeTodoTeamInteractor;
    }

    public void execute(int index, String name, String desc, LocalDateTime start, LocalDateTime end, String requester, String requestedTo, String team)
    {
        CompleteTodoTeamInputData input = new CompleteTodoTeamInputData(
                index,
                name,
                desc,
                start,
                end,
                requester,
                requestedTo,
                team
        );
        completeTodoTeamInteractor.execute(input);
    }

}
