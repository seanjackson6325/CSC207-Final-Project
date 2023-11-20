package use_case.DeleteTodo;

import entity.Team;
import entity.Todo;
import java.util.List;

public class DeleteTodoInputData {

    final private int index;
    final private List<Todo> list;
    final private Team team;

    public DeleteTodoInputData(int index, List<Todo> list, Team team) {
        this.index = index;
        this.list = list;
        this.team = team;
    }

    int getIndex() {
        return index;
    }
    List<Todo> getList() {
        return list;
    }

    Team getTeam() {
        return team;
    }
}
