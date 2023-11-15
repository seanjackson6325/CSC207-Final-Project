package use_case.CreateTodoUser;

import Factory.TodoFactory;
import data_access.DataAccessInterface;
import entity.Todo;
import entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateTodoUserInteractor implements CreateTodoUserInputBoundary {
    final DataAccessInterface userDataAccessObject;
    final CreateTodoUserOutputBoundary userPresenter;
    public CreateTodoUserInteractor(DataAccessInterface userDataAccessInterface,
                                    CreateTodoUserOutputBoundary userOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.userPresenter = userOutputBoundary;
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
    public void execute(CreateTodoUserInputData inputData) {
        String name = inputData.getName();
        String desc = inputData.getDescription();
        LocalDateTime start = inputData.getStartTime();
        LocalDateTime end = inputData.getEndTime();
        User requestedTo = userDataAccessObject.readUser(inputData.getRequestedTo());
        Boolean status = inputData.getStatus();
        User user = userDataAccessObject.readUser(inputData.getUser());

        TodoFactory todoFactory = new TodoFactory();
        Todo newTodo = todoFactory.create(name, desc, start, end, user.getUsername(), requestedTo.getUsername(), status);

        List<Todo> taskList = user.getTaskList();
        for (Todo task : taskList) { // Check for duplicate name
            if (name.equals(task.getName())) {
                CreateTodoUserOutputData outputData = new CreateTodoUserOutputData("There is another task with the same name!");
                this.userPresenter.failureView(outputData);
                return;
            }
        }
        if (emptyName(name)) { // Check if the task has a name
            CreateTodoUserOutputData outputData = new CreateTodoUserOutputData("Task does not have a name!");
            this.userPresenter.failureView(outputData);
        } else if (start.isAfter(end)) { // Check if the start date is after the end date
            CreateTodoUserOutputData outputData = new CreateTodoUserOutputData("Invalid start & end dates!");
            this.userPresenter.failureView(outputData);
        } else if (start.isBefore(LocalDateTime.now())) { // Check if the start date is equal to or after the current date
            CreateTodoUserOutputData outputData = new CreateTodoUserOutputData("Invalid start date!");
            this.userPresenter.failureView(outputData);
        } else {
            CreateTodoUserOutputData outputData = new CreateTodoUserOutputData("Task Successfully Created!");
            user.addTask(newTodo);
            this.userPresenter.successView(outputData);
        }
    }
}
