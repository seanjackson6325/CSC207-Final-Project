package use_case.CompleteTodoTeam;

import Factory.TodoFactory;
import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;
import entity.Todo;
import entity.User;
import interface_adapter.createTeam.TeamViewModel;
import use_case.CreateTodoTeam.CreateTodoTeamOutputBoundary;
import use_case.EditTodoTeam.EditTodoTeamOutputData;

import java.time.LocalDateTime;

public class CompleteTodoTeamInteractor implements CompleteTodoTeamInputBoundary{

    private DataAccessInterface dataAccessInterface;
    private CompleteTodoTeamOutputBoundary completeTodoTeamPresenter;

    public CompleteTodoTeamInteractor(DataAccessInterface dataAccessInterface,
                                      CompleteTodoTeamOutputBoundary completeTodoTeamPresenter)
    {
        this.dataAccessInterface = dataAccessInterface;
        this.completeTodoTeamPresenter = completeTodoTeamPresenter;
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
    public void execute(CompleteTodoTeamInputData inputData) {
        int index = inputData.getIndex();
        String name = inputData.getName();
        String desc = inputData.getDescription();
        LocalDateTime start = inputData.getStartTime();
        LocalDateTime end = inputData.getEndTime();
        String requester = inputData.getRequester();
        String requestedTo = inputData.getRequestedTo();
        Team team = dataAccessInterface.readTeam(inputData.getTeam());
        User user = EntityMemory.getLoggedInUser();

        TodoFactory todoFactory = new TodoFactory();
        Todo newTodo = todoFactory.create(name, desc, start, end, requester, requestedTo, true);

        if (emptyName(name)) { // Check if the task has a name
            this.completeTodoTeamPresenter.prepareFailView("Task does not have a name!");
        } else if (start.isAfter(end)) { // Check if the start date is after the end date
            this.completeTodoTeamPresenter.prepareFailView("Invalid start & end dates!");
        } else if (start.isBefore(LocalDateTime.now())) { // Check if the start date is equal to or after the current date
            this.completeTodoTeamPresenter.prepareFailView("Invalid start date!");
        } else {
            CompleteTodoTeamOutputData output = new CompleteTodoTeamOutputData("Task Successfully Completed!");
            team.getTeamTasks().remove(index);
            team.getTeamTasks().add(newTodo);
            team.setTeamTasks(team.getTeamTasks());
            dataAccessInterface.updateTeam(team);
            EntityMemory.setLoggedInUser(user);
            this.completeTodoTeamPresenter.prepareSuccessView(output);
        }
    }
}
