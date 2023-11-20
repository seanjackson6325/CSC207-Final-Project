package use_case.DeleteTodo;

public interface DeleteTodoOutputBoundary {

    void prepareSuccessView(DeleteTodoOutputData deleteTodoOutputData);

    void prepareFailView(String error);
}
