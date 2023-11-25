package interface_adapter.user;

import interface_adapter.deleteTodo.DeleteTodoController;
import use_case.CreateTodoUser.CreateTodoUserInputBoundary;
import use_case.CreateTodoUser.CreateTodoUserInputData;
import use_case.EditTodo.EditTodoInputBoundary;
import use_case.EditTodo.EditTodoInputData;
import use_case.MarkDone.MarkDoneInputBoundary;
import use_case.MarkDone.MarkDoneInputData;

import java.time.LocalDateTime;

public class UserController {

    private final CreateTodoUserInputBoundary createTodoUserInputBoundary;
    private final EditTodoInputBoundary editTodoInputBoundary;

    private final MarkDoneInputBoundary markDoneInputBoundary;

    public UserController(CreateTodoUserInputBoundary createTodoUserInputBoundary, EditTodoInputBoundary editTodoInputBoundary,
                            MarkDoneInputBoundary markDoneInputBoundary)
    {
        this.createTodoUserInputBoundary = createTodoUserInputBoundary;
        this.editTodoInputBoundary = editTodoInputBoundary;
        this.markDoneInputBoundary = markDoneInputBoundary;
    }

    public void executeAdd
            (
                    String name,
                    String description,
                    LocalDateTime start,
                    LocalDateTime end,
                    String requestedTo,
                    boolean status,
                    String user
            )
    {
        CreateTodoUserInputData input = new CreateTodoUserInputData
                (
                        name,
                        description,
                        start,
                        end,
                        requestedTo,
                        status,
                        user
                );
        createTodoUserInputBoundary.execute(input);
    }

    public void executeEdit
            (
                    String prevName,
                    String newName,
                    String description,
                    LocalDateTime start,
                    LocalDateTime end,
                    String requestedTo,
                    boolean status,
                    String user
            )
    {
        EditTodoInputData input = new EditTodoInputData
                (
                        prevName,
                        newName,
                        description,
                        start,
                        end,
                        requestedTo,
                        status,
                        user
                );
        editTodoInputBoundary.execute(input);
    }

    public void executeMarkDone(String todoName)
    {
        MarkDoneInputData input = new MarkDoneInputData(todoName);
        markDoneInputBoundary.execute(input);
    }
}
