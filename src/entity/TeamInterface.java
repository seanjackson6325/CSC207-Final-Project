package entity;
import java.util.List;

public interface TeamInterface  {

    String getUsername();

    String getPassword();

    List<Todo> taskList();

    List<Team> getTeam();


}