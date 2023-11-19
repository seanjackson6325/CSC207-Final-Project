package interface_adapter.user;

import use_case.CreateTodoUser.CreateTodoUserOutputBoundary;
import use_case.CreateTodoUser.CreateTodoUserOutputData;
import view.ViewManager;

public class UserPresenter implements CreateTodoUserOutputBoundary
{
    public UserPresenter(ViewManager viewManager, UserViewModel viewModel)
    {

    }


    @Override
    public void successView(CreateTodoUserOutputData outputData) {

    }

    @Override
    public void failureView(CreateTodoUserOutputData outputData) {

    }
}
