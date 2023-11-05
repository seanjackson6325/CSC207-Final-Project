package use_case.CreateTodoTeam;

import entity.Team;

public interface CreateTodoTeamDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Team team);
}
