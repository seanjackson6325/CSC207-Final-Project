package use_case.CreateTodoTeam;

import entity.Team;

import java.time.LocalDateTime;

public class CreateTodoTeamInputData {
    final private String name;
    final private String description;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private String requestedTo;
    final private Boolean status;
    final private String team;

    public CreateTodoTeamInputData(String name, String desc, LocalDateTime start, LocalDateTime end, String requestedTo, Boolean status, String team) {
        this.name = name;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
        this.requestedTo = requestedTo;
        this.status = status;
        this.team = team;
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
