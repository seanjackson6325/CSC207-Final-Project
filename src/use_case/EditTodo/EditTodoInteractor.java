package use_case.EditTodo;

import Factory.TodoFactory;
import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Todo;
import entity.User;
import use_case.CreateTodoUser.CreateTodoUserOutputData;

import java.time.LocalDateTime;
import java.util.List;


public class EditTodoInteractor implements EditTodoInputBoundary {

    private final EditTodoOutputBoundary editTodoPresenter;
    private final DataAccessInterface dataAccess;

    public EditTodoInteractor(DataAccessInterface dataAccess,
                              EditTodoOutputBoundary editTodoPresenter) {
        this.dataAccess = dataAccess;
        this.editTodoPresenter = editTodoPresenter;
    }

    private boolean emptyName(String name) {
        for (int i = 0; i < name.length()-1; i++) {
            if (name.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    @Override
    public void execute(EditTodoInputData inputData) {
        String prevName = inputData.getPrevName();
        String newName = inputData.getNewName();
        String desc = inputData.getDescription();
        LocalDateTime start = inputData.getStartTime();
        LocalDateTime end = inputData.getEndTime();
        User requestedTo = dataAccess.readUser(inputData.getRequestedTo());
        Boolean status = inputData.getStatus();
        User user = dataAccess.readUser(inputData.getUser());

        TodoFactory todoFactory = new TodoFactory();
        Todo newTodo = todoFactory.create(newName, desc, start, end, user.getUsername(), requestedTo.getUsername(), status);

        List<Todo> taskList = user.getTaskList();
        for (Todo task : taskList) { // Check for duplicate name
            if (newName.equals(task.getName()) && !prevName.equals(newName)) {
                EditTodoOutputData outputData = new EditTodoOutputData("There is another task with the same name!");
                this.editTodoPresenter.editFailureView(outputData);
                return;
            }
        }
        if (emptyName(newName)) { // Check if the task has a name
            EditTodoOutputData outputData = new EditTodoOutputData("Task does not have a name!");
            this.editTodoPresenter.editFailureView(outputData);
        } else if (start.isAfter(end)) { // Check if the start date is after the end date
            EditTodoOutputData outputData = new EditTodoOutputData("Invalid start & end dates!");
            this.editTodoPresenter.editFailureView(outputData);
        } else if (start.isBefore(LocalDateTime.now())) { // Check if the start date is equal to or after the current date
            EditTodoOutputData outputData = new EditTodoOutputData("Invalid start date!");
            this.editTodoPresenter.editFailureView(outputData);
        } else {
            EditTodoOutputData outputData = new EditTodoOutputData("Task Successfully Edited!");
            for (int index = 0; index < user.getTaskList().size(); index++) {
                if (prevName.equals(user.getTaskList().get(index).getName())) {
                    user.getTaskList().set(index, newTodo);
                }
            }
            user.setTaskList(user.getTaskList());
            dataAccess.updateUser(user);
            EntityMemory.setLoggedInUser(user);
            this.editTodoPresenter.editSuccessView(outputData);
        }
    }
}
