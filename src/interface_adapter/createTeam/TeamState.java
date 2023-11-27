package interface_adapter.createTeam;

public class TeamState
{
    String addTeamUsernameInput;

    private boolean isCreateTeamFailed;

    public TeamState()
    {
        addTeamUsernameInput = "";
        isCreateTeamFailed = false;
    }


    public boolean getIsCreateTeamFailed()
    {
        return isCreateTeamFailed;
    }

    public void setIsCreateTeamFailed(boolean isCreateTeamFailed)
    {
        this.isCreateTeamFailed = isCreateTeamFailed;
    }

    public String getAddTeamUsernameInput()
    {
        return addTeamUsernameInput;
    }

    public void setAddTeamUsernameInput(String name)
    {
        addTeamUsernameInput = name;
    }


}
