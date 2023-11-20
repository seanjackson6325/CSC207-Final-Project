package use_case.CreateTeam;

import entity.Team;
import entity.Todo;

import java.util.List;

public class CreateTeamInputData {

    private final String teamName;

    public CreateTeamInputData(String teamName) {
        this.teamName = teamName;

    }

    String getTeamName() {
        return teamName;
    }
}
