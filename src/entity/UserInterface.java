package entity;

import java.util.List;

public interface UserInterface {


    String getUsername();

    String getPassword();

    List<Todo> getTaskList();

    List<Team> getTeam();

    void SetTaskList(List<Todo> taskList);

    void SetTeams(List<Team> teams);
}
