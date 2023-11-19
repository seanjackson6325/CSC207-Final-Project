package use_case.RemoveMember;

public class RemoveMemberInputData {
    final private String user;
    final private String team;

    public RemoveMemberInputData(String user, String team) {
        this.user = user;
        this.team = team;
    }

    public String getUser() {
        return user;
    }

    public String getTeam() {
        return team;
    }
}
