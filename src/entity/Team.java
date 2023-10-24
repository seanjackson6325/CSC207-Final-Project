package entity;

import java.util.List;

public class Team implements TeamInterface {

    private String teamName;
    private List<Todo> teamTasks;
    private List<User> managers;
    private List<User> members;

    /**
     * Constructor for Team()
     *
     * @param teamName   Name of the Team
     * @param teamTasks   List of To-do that the team has to complete
     * @param managers   List of Users that are managers
     * @param members   List of Users that are members
     *
     */
    public Team(String teamName, List<Todo> teamTasks, List<User> managers, List<User> members) {
        this.teamName = teamName;
        this.teamTasks = teamTasks;
        this.managers = managers;
        this.members = members;
    }

    @Override
    public String getTeamName() {
        return this.teamName;
    }

    @Override
    public List<Todo>  getTeamTasks() {
        return this.teamTasks;
    }

    @Override
    public List<User> getManagers() {
        return this.managers;
    }

    @Override
    public List<User> getMembers() {
        return this.members;
    }

    @Override
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public void setTeamTasks(List<Todo> teamTasks) {
        this.teamTasks = teamTasks;
    }

    @Override
    public void setManagers(List<User> managers) {
        this.managers = managers;
    }

    @Override
    public void setMembers(List<User> members) {
        this.managers = members;
    }

}
