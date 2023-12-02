package use_case.EditTodoTeam;

import java.time.LocalDateTime;

public class EditTodoTeamInputData {

    private int index;
    final private String name;
    final private String description;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private String requestedTo;
    final private String requester;
    final private Boolean status;
    final private String team;

    public EditTodoTeamInputData(int index, String name, String desc, LocalDateTime start, LocalDateTime end, String requester, String requestedTo, Boolean status, String team) {
        this.index = index;
        this.name = name;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
        this.requestedTo = requestedTo;
        this.requester = requester;
        this.status = status;
        this.team = team;
    }

    public int getIndex()
    {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getRequester() {
        return requester;
    }

    public String getRequestedTo() {
        return requestedTo;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getTeam() {
        return team;
    }

}
