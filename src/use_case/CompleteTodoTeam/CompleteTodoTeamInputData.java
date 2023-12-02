package use_case.CompleteTodoTeam;

import java.time.LocalDateTime;

public class CompleteTodoTeamInputData {

    private int index;
    final private String name;
    final private String description;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private String requestedTo;
    final private String requester;
    final private String team;

    public CompleteTodoTeamInputData(int index, String name, String desc, LocalDateTime start, LocalDateTime end, String requester, String requestedTo, String team) {
        this.index = index;
        this.name = name;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
        this.requestedTo = requestedTo;
        this.requester = requester;
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

    public String getTeam() {
        return team;
    }

}
