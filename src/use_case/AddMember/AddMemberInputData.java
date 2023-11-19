package use_case.AddMember;

import java.time.LocalDateTime;

public class AddMemberInputData {
    final private String user;
    final private String team;

    public AddMemberInputData(String user, String team) {
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
