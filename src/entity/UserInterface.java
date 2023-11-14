package entity;

import java.util.List;

public interface UserInterface {


    String getUsername();

    String getPassword();

    List<Todo> getTaskList();

    List<String> getTeam();

    void setTaskList(List<Todo> taskList);

    void setTeams(List<String> teams);
    void addTask(Todo task);
}
