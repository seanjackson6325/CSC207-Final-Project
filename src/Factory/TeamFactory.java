package Factory;

import entity.Todo;
import entity.Team;

import java.util.List;

public class TeamFactory {
    public Team create(String teamName, List<Todo> teamTasks, List<String> managers, List<String> members) {
        return new Team(teamName, teamTasks, managers, members);
    }
}
