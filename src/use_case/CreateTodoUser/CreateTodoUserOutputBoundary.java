package use_case.CreateTodoUser;

public interface CreateTodoUserOutputBoundary {
    void successView(CreateTodoUserOutputData outputData);
    void failureView(CreateTodoUserOutputData outputData);
}