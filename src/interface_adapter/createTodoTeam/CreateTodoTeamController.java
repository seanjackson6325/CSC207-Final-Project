package interface_adapter.createTodoTeam;

import use_case.CreateTeam.CreateTeamInputBoundary;
import use_case.CreateTeam.CreateTeamInputData;
import use_case.CreateTodoTeam.CreateTodoTeamInputBoundary;
import use_case.CreateTodoTeam.CreateTodoTeamInputData;
import use_case.CreateTodoTeam.CreateTodoTeamInteractor;
import use_case.CreateTodoTeam.CreateTodoTeamInputData;

import java.time.LocalDateTime;

public class CreateTodoTeamController {

    final CreateTodoTeamInputBoundary createTodoTeamInteractor;

    public CreateTodoTeamController(CreateTodoTeamInputBoundary createTodoTeamInteractor)
    {
        this.createTodoTeamInteractor = createTodoTeamInteractor;
    }

    public void execute
            (
                    String name,
                    String description,
                    LocalDateTime start,
                    LocalDateTime end,
                    String requester,
                    String requestedTo,
                    boolean status,
                    String team
            )
    {
        CreateTodoTeamInputData input = new CreateTodoTeamInputData(
                name,
                description,
                start,
                end,
                requester,
                requestedTo,
                status,
                team
        );
        createTodoTeamInteractor.execute(input);
    }


}
