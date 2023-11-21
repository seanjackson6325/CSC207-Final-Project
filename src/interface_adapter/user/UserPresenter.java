package interface_adapter.user;

import app.EntityMemory;
import use_case.CreateTodoUser.CreateTodoUserOutputBoundary;
import use_case.CreateTodoUser.CreateTodoUserOutputData;
import use_case.EditTodo.EditTodoOutputBoundary;
import use_case.EditTodo.EditTodoOutputData;
import view.ViewManager;

import javax.swing.*;

public class UserPresenter implements CreateTodoUserOutputBoundary, EditTodoOutputBoundary
{
    ViewManager viewManager;
    UserViewModel userViewModel;

    public UserPresenter(ViewManager viewManager, UserViewModel userViewModel)
    {
        this.viewManager = viewManager;
        this.userViewModel = userViewModel;
    }


    @Override
    public void createSuccessView(CreateTodoUserOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
        userViewModel.setLoggedInUser(EntityMemory.getLoggedInUser());
        userViewModel.getState().setFailed(false);
    }

    @Override
    public void createFailureView(CreateTodoUserOutputData outputData) {
        JOptionPane.showMessageDialog(null, outputData.getMessage());
        userViewModel.getState().setFailed(true);
    }

    @Override
    public void editSuccessView(EditTodoOutputData editTodoOutputData) {
        JOptionPane.showMessageDialog(null, editTodoOutputData.getMessage());
        userViewModel.setLoggedInUser(EntityMemory.getLoggedInUser());
        userViewModel.getState().setFailed(false);
    }

    @Override
    public void editFailureView(EditTodoOutputData editTodoOutputData) {
        JOptionPane.showMessageDialog(null, editTodoOutputData.getMessage());
        userViewModel.getState().setFailed(true);
    }
}
