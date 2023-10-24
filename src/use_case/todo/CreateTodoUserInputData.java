package use_case.todo;

import java.time.LocalDateTime;

public class CreateTodoUserInputData {
    final private String name;
    final private String description;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private Boolean status;
    final private Object user;

    public CreateTodoUserInputData(String name, String desc, LocalDateTime start, LocalDateTime end, Boolean status, Object user) {
        this.name = name;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
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

    public Boolean getStatus() {
        return status;
    }

    public Object getUser() {
        return user;
    }
}
