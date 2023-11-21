package use_case.EditTodo;

import entity.Team;
import entity.Todo;

import java.time.LocalDateTime;
import java.util.List;

public class EditTodoInputData {

    final private String prevName;
    final private String newName;
    final private String description;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private String requestedTo;
    final private Boolean status;
    final private String user;

    public EditTodoInputData(String prevName, String newName, String desc, LocalDateTime start, LocalDateTime end, String requestedTo, Boolean status, String user) {
        this.prevName = prevName;
        this.newName = newName;
        this.description = desc;
        this.startTime = start;
        this.endTime = end;
        this.requestedTo = requestedTo;
        this.status = status;
        this.user = user;
    }

    public String getPrevName() {
        return prevName;
    }
    public String getNewName() {
        return newName;
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

