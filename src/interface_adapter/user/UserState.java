package interface_adapter.user;

import java.time.LocalDateTime;

public class UserState {

    private String todoName;
    private String todoDescription;
    private LocalDateTime todoStartTime, todoEndTime;
    private String todoUser;
    private String todoRequestedTo;
    private boolean todoStatus;

    private boolean failed;

    public UserState() {}

    public UserState(UserState prev)
    {
        todoName = prev.getTodoName();
        todoDescription = prev.getTodoDescription();
        todoStartTime = prev.getTodoStartTime();
        todoEndTime = prev.getTodoEndTime();
        todoUser = prev.getTodoUser();
        todoRequestedTo = prev.getTodoRequestedTo();
        todoStatus = prev.getTodoStatus();
        failed = prev.isFailed();
    }

    public String getTodoName()
    {
        return todoName;
    }

    public void setTodoName(String todoName)
    {
        this.todoName = todoName;
    }

    public String getTodoDescription()
    {
        return todoDescription;
    }

    public void setTodoDescription(String description)
    {
        this.todoDescription = description;
    }

    public LocalDateTime getTodoStartTime()
    {
        return todoStartTime;
    }

    public void setTodoStartTime(LocalDateTime startTime)
    {
        this.todoStartTime = startTime;
    }

    public LocalDateTime getTodoEndTime()
    {
        return todoEndTime;
    }

    public void setTodoEndTime(LocalDateTime endTime)
    {
        this.todoEndTime = endTime;
    }

    public String getTodoUser()
    {
        return todoUser;
    }

    public void setTodoUser(String user)
    {
        this.todoUser = user;
    }

    public String getTodoRequestedTo()
    {
        return this.todoRequestedTo;
    }

    public void setTodoRequestedTo(String requestedTo)
    {
        this.todoRequestedTo = requestedTo;
    }

    public boolean getTodoStatus()
    {
        return todoStatus;
    }

    public void setTodoStatus(boolean status)
    {
        this.todoStatus = status;
    }

    public void setFailed(boolean status)
    {
        failed = status;
    }

    public boolean isFailed()
    {
        return failed;
    }

}
