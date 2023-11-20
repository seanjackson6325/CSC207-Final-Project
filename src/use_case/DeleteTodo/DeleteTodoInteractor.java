package use_case.DeleteTodo;

import app.EntityMemory;
import data_access.DataAccessInterface;


public class DeleteTodoInteractor implements DeleteTodoInputBoundary {

    private final DeleteTodoOutputBoundary deleteTodoPresenter;
    private final DataAccessInterface dataAccess;

    public DeleteTodoInteractor(DataAccessInterface dataAccess,
                                DeleteTodoOutputBoundary deleteTodoPresenter) {
        this.dataAccess = dataAccess;
        this.deleteTodoPresenter = deleteTodoPresenter;
    }

    @Override
    public void execute(DeleteTodoInputData deleteTodoInputData) {

        try {
            // remove to-do
            deleteTodoInputData.getList().remove(deleteTodoInputData.getIndex());

            // update the change in the Database
            if (deleteTodoInputData.getTeam() == null) {
                dataAccess.updateUser(EntityMemory.getLoggedInUser());
            }
            else {
                dataAccess.updateTeam(deleteTodoInputData.getTeam());
            }

            deleteTodoPresenter.prepareSuccessView(new DeleteTodoOutputData("Successfully Deleted Todo From List"));

        }
        catch (Exception e) {
            deleteTodoPresenter.prepareFailView("Failed to Delete Todo From List");
        }
    }
}
