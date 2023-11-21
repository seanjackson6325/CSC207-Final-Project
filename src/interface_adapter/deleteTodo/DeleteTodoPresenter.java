package interface_adapter.deleteTodo;

import interface_adapter.user.UserViewModel;
import use_case.DeleteTodo.DeleteTodoOutputBoundary;
import use_case.DeleteTodo.DeleteTodoOutputData;
import view.ViewManager;

public class DeleteTodoPresenter implements DeleteTodoOutputBoundary {

    private ViewManager viewManager;
    private UserViewModel userViewModel;

    public DeleteTodoPresenter(ViewManager viewManager, UserViewModel userViewModel) {
        this.viewManager = viewManager;
        this.userViewModel = userViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteTodoOutputData deleteTodoOutputData) {
        // this gets the success message, deleteTodoOutputData.getMessage();
        // probably want reload/update the view we are in

    }

    @Override
    public void prepareFailView(String error) {

    }
}
