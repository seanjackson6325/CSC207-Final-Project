package use_case.CreateTodoUser;

import entity.User;

import java.time.LocalDateTime;

public class CreateTodoUserInputData {
    final private String name;
    final private String description;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private String requestedTo;
    final private Boolean status;
    final private String user;

    public CreateTodoUserInputData(String name, String desc, LocalDateTime start, LocalDateTime end, String requestedTo, Boolean status, String user) {
        this.name = name;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
        this.requestedTo = requestedTo;
        this.status = status;
        this.user = user;
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

    public String getUser() {
        return user;
    }
}
