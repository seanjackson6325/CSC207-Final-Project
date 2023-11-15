package use_case.CreateTodoTeam;

import Factory.TodoFactory;
import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;
import entity.Todo;
import entity.User;
import use_case.CreateTodoUser.CreateTodoUserOutputData;

import java.time.LocalDateTime;
import java.util.List;

public class CreateTodoTeamInteractor implements CreateTodoTeamInputBoundary {
    final DataAccessInterface teamDataAccessObject;
    final CreateTodoTeamOutputBoundary teamPresenter;
    public CreateTodoTeamInteractor(DataAccessInterface teamDataAccessInterface,
                                    CreateTodoTeamOutputBoundary teamOutputBoundary) {
        this.teamDataAccessObject = teamDataAccessInterface;
        this.teamPresenter = teamOutputBoundary;
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
    public void execute(CreateTodoTeamInputData inputData) {
        String name = inputData.getName();
        String desc = inputData.getDescription();
        LocalDateTime start = inputData.getStartTime();
        LocalDateTime end = inputData.getEndTime();
        Team requestedTo = teamDataAccessObject.readTeam(inputData.getRequestedTo());
        Boolean status = inputData.getStatus();
        Team team = teamDataAccessObject.readTeam(inputData.getTeam());
        User user = EntityMemory.getLoggedInUser();

        TodoFactory todoFactory = new TodoFactory();
        Todo newTodo = todoFactory.create(name, desc, start, end, team.getTeamName(), requestedTo.getTeamName(), status);

        List<Todo> taskList = user.getTaskList();
        for (Todo task : taskList) { // Check for duplicate name
            if (name.equals(task.getName())) {
                CreateTodoTeamOutputData outputData = new CreateTodoTeamOutputData("There is another task with the same name!");
                this.teamPresenter.failureView(outputData);
                return;
            }
        }
        if (emptyName(name)) { // Check if the task has a name
            CreateTodoTeamOutputData outputData = new CreateTodoTeamOutputData("Task does not have a name!");
            this.teamPresenter.failureView(outputData);
        } else if (start.isAfter(end)) { // Check if the start date is after the end date
            CreateTodoTeamOutputData outputData = new CreateTodoTeamOutputData("Invalid start & end dates!");
            this.teamPresenter.failureView(outputData);
        } else if (start.isBefore(LocalDateTime.now())) { // Check if the start date is equal to or after the current date
            CreateTodoTeamOutputData outputData = new CreateTodoTeamOutputData("Invalid start date!");
            this.teamPresenter.failureView(outputData);
        } else {
            CreateTodoTeamOutputData outputData = new CreateTodoTeamOutputData("Task Successfully Created!");
            user.getTaskList().add(newTodo);
            this.teamPresenter.successView(outputData);
        }
    }
}