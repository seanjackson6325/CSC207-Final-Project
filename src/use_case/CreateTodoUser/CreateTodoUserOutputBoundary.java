package use_case.CreateTodoUser;

public interface CreateTodoUserOutputBoundary {
    void createSuccessView(CreateTodoUserOutputData outputData);
    void createFailureView(CreateTodoUserOutputData outputData);
}