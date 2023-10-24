package entity;

import java.util.List;

public interface TeamInterface  {

    String getTeamName();

    List<Todo>  getTeamTasks();

    List<User> getManagers();

    List<User> getMembers();

    void setTeamName(String teamName);

    void setTeamTasks(List<Todo>  teamTasks);

    void setManagers(List<User> managers);

    void setMembers(List<User> members);

}