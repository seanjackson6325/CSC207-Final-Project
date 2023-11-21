package use_case.EditTodo;

public interface EditTodoOutputBoundary {

    void editSuccessView(EditTodoOutputData editTodoOutputData);

    void editFailureView(EditTodoOutputData editTodoOutputData);
}
