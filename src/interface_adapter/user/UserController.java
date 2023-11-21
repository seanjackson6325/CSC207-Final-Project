package interface_adapter.user;

import interface_adapter.deleteTodo.DeleteTodoController;
import use_case.CreateTodoUser.CreateTodoUserInputBoundary;
import use_case.CreateTodoUser.CreateTodoUserInputData;

import java.time.LocalDateTime;

public class UserController {

    private final CreateTodoUserInputBoundary createTodoUserInputBoundary;

    public UserController(CreateTodoUserInputBoundary createTodoUserInputBoundary)
    {
        this.createTodoUserInputBoundary = createTodoUserInputBoundary;
    }

    public void execute
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
}
