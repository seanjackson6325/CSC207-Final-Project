package use_case.CreateTodoUser;

import data_access.DataAccessInterface;

public class CreateTodoUserInteractor implements CreateTodoUserInputBoundary {
    final DataAccessInterface userDataAccessObject;
    final CreateTodoUserOutputBoundary userPresenter;
    public CreateTodoUserInteractor(DataAccessInterface userDataAccessInterface,
                                    CreateTodoUserOutputBoundary userOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = userOutputBoundary;
    }

    @Override
    public void execute(CreateTodoUserInputData inputData) {

    }
}
