package use_case.CreateTodoUser;

public class CreateTodoUserInteractor implements CreateTodoUserInputBoundary {
    final CreateTodoUserDataAccessInterface userDataAccessObject;
    final CreateTodoUserOutputBoundary userPresenter;
    public CreateTodoUserInteractor(CreateTodoUserDataAccessInterface userDataAccessInterface,
                                    CreateTodoUserOutputBoundary userOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = userOutputBoundary;
    }

    @Override
    public void execute(CreateTodoUserInputData inputData) {

    }
}
