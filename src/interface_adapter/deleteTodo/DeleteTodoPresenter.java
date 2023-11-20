package interface_adapter.deleteTodo;

import use_case.DeleteTodo.DeleteTodoOutputBoundary;
import use_case.DeleteTodo.DeleteTodoOutputData;

public class DeleteTodoPresenter implements DeleteTodoOutputBoundary {

    public DeleteTodoPresenter() {
        //TODO
    }

    @Override
    public void prepareSuccessView(DeleteTodoOutputData deleteTodoOutputData) {
        // this gets the success message, deleteTodoOutputData.getMessage();
        // probably want reload/update the view we are in

        //TODO
    }

    @Override
    public void prepareFailView(String error) {
        //TODO

    }
}
