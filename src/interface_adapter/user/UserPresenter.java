package interface_adapter.user;

import app.EntityMemory;
import use_case.CreateTodoUser.CreateTodoUserOutputBoundary;
import use_case.CreateTodoUser.CreateTodoUserOutputData;
import view.ViewManager;

import javax.swing.*;

public class UserPresenter implements CreateTodoUserOutputBoundary
{
    ViewManager viewManager;
    UserViewModel userViewModel;

    public UserPresenter(ViewManager viewManager, UserViewModel userViewModel)
    {
        this.viewManager = viewManager;
        this.userViewModel = userViewModel;
    }


    @Override
    public void successView(CreateTodoUserOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
        userViewModel.setLoggedInUser(EntityMemory.getLoggedInUser());
    }

    @Override
    public void failureView(CreateTodoUserOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
    }
}
