package use_case.MarkDone;

import Factory.TodoFactory;
import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;
import entity.Todo;
import entity.User;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class MarkDoneInteractor implements MarkDoneInputBoundary {
    final DataAccessInterface dataAccessObject;
    final MarkDoneOutputBoundary markPresenter;
    public MarkDoneInteractor(DataAccessInterface dataAccessObject,
                              MarkDoneOutputBoundary markPresenter) {
        this.dataAccessObject = dataAccessObject;
        this.markPresenter = markPresenter;
    }

    @Override
    public void execute(MarkDoneInputData inputData) {
        User user = EntityMemory.getLoggedInUser();
        Todo todo = null;

        for (Todo currTodo : user.getTaskList()) {
            if (currTodo.getName().equals(inputData.getTodoName())) {
                todo = currTodo;
            }
        }

        if (todo != null) {
            TodoFactory todoFactory = new TodoFactory();
            Todo newTodo = todoFactory.create(todo.getName(), todo.getDescription(), todo.getStartTime(), todo.getEndTime(), todo.getRequester(), todo.getRequestedTo(), true);
            for (int index = 0; index < user.getTaskList().size(); index++) {
                if (todo.getName().equals(user.getTaskList().get(index).getName())) {
                    user.getTaskList().set(index, newTodo);
                }
            }
            MarkDoneOutputData outputData = new MarkDoneOutputData("Task Completed!");
            user.setTaskList(user.getTaskList());
            dataAccessObject.updateUser(user);
            EntityMemory.setLoggedInUser(user);
            this.markPresenter.successView(outputData);
        }
    }
}