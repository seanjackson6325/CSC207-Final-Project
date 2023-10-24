package entity;

import java.util.List;

public interface TeamInterface  {

    String getTeamName();

    List<Todo>  getTeamTasks();

    List<String> getManagers();

    List<String> getMembers();

    void setTeamTasks(List<Todo>  teamTasks);

    void setManagers(List<String> managers);

    void setMembers(List<String> members);

}