package interface_adapter.user;

import use_case.CreateTodoUser.CreateTodoUserInputBoundary;
import use_case.CreateTodoUser.CreateTodoUserInputData;
import use_case.EditTodo.EditTodoInputBoundary;
import use_case.EditTodo.EditTodoInputData;

import java.time.LocalDateTime;

public class UserController {

    private final CreateTodoUserInputBoundary createTodoUserInputBoundary;
    private final EditTodoInputBoundary editTodoInputBoundary;

    public UserController(CreateTodoUserInputBoundary createTodoUserInputBoundary, EditTodoInputBoundary editTodoInputBoundary)
    {
        this.createTodoUserInputBoundary = createTodoUserInputBoundary;
        this.editTodoInputBoundary = editTodoInputBoundary;
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
}
