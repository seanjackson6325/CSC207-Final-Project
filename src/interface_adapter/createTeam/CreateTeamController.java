package interface_adapter.createTeam;

import use_case.CreateTeam.CreateTeamInputBoundary;
import use_case.CreateTeam.CreateTeamInputData;


public class CreateTeamController {

    final CreateTeamInputBoundary createTeamInteractor;

    public CreateTeamController(CreateTeamInputBoundary createTeamInteractor)
    {
        this.createTeamInteractor = createTeamInteractor;
    }

    /**
     * @param teamName: the name of the Team
     */
    public void execute(String teamName) {
        CreateTeamInputData input = new CreateTeamInputData(teamName);
        createTeamInteractor.execute(input);
    }
}
