package use_case.DeleteTodoTeam;

import entity.Team;
import entity.Todo;

import java.util.List;

public class DeleteTodoTeamInputData {

    private final int todoIndex;
    private final String teamName;

    public DeleteTodoTeamInputData(int todoIndex, String teamName) {
        this.todoIndex = todoIndex;
        this.teamName = teamName;
    }

    public int getIndex() {
        return todoIndex;
    }

    public String getTeamName()
    {
        return teamName;
    }

}
