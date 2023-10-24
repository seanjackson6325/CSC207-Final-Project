package entity;

import java.util.List;

public class User implements UserInterface {

    private final String username;
    private final String password;
    private List<Todo> taskList;
    private List<Team> teams;

    /**
     * Constructor for User()
     *
     * @param username   username of the User
     * @param password   password of the User
     * @param taskList   List of To-do for the User
     * @param teams   teams that the User is in
     *
     */
    public User(String username, String password, List<Todo> taskList, List<Team> teams) {
        this.username = username;
        this.password = password;
        this.taskList = taskList;
        this.teams = teams;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public List<Todo> getTaskList() {
        return this.taskList;
    }

    @Override
    public List<Team> getTeam() {
        return this.teams;
    }

    @Override
    public void SetTaskList(List<Todo> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void SetTeams(List<Team> teams) {
        this.teams = teams;
    }
}
