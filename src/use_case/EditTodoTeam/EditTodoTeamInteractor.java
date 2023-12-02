package use_case.EditTodoTeam;

import Factory.TodoFactory;
import app.EntityMemory;
import data_access.DataAccessInterface;
import entity.Team;
import entity.Todo;
import entity.User;
import use_case.CreateTodoTeam.CreateTodoTeamInputData;
import use_case.CreateTodoTeam.CreateTodoTeamOutputData;
import use_case.CreateTodoUser.CreateTodoUserInputData;
import use_case.CreateTodoUser.CreateTodoUserOutputData;

import java.time.LocalDateTime;
import java.util.List;

public class EditTodoTeamInteractor implements EditTodoTeamInputBoundary {

    private DataAccessInterface dataAccessInterface;
    private EditTodoTeamOutputBoundary editTodoTeamPresenter;

    public EditTodoTeamInteractor(DataAccessInterface dataAccessInterface,
                                  EditTodoTeamOutputBoundary editTodoTeamPresenter)
    {
        this.dataAccessInterface = dataAccessInterface;
        this.editTodoTeamPresenter = editTodoTeamPresenter;
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
    public void execute(EditTodoTeamInputData inputData) {
        int index = inputData.getIndex();
        String name = inputData.getName();
        String desc = inputData.getDescription();
        LocalDateTime start = inputData.getStartTime();
        LocalDateTime end = inputData.getEndTime();
        String requester = inputData.getRequester();
        String requestedTo = inputData.getRequestedTo();
        Boolean status = inputData.getStatus();
        Team team = dataAccessInterface.readTeam(inputData.getTeam());
        User user = EntityMemory.getLoggedInUser();

        TodoFactory todoFactory = new TodoFactory();
        Todo newTodo = todoFactory.create(name, desc, start, end, requester, requestedTo, status);

        if (emptyName(name)) { // Check if the task has a name
            this.editTodoTeamPresenter.prepareFailView("Task does not have a name!");
        } else if (start.isAfter(end)) { // Check if the start date is after the end date
            this.editTodoTeamPresenter.prepareFailView("Invalid start & end dates!");
        } else if (start.isBefore(LocalDateTime.now())) { // Check if the start date is equal to or after the current date
            this.editTodoTeamPresenter.prepareFailView("Invalid start date!");
        } else {
            EditTodoTeamOutputData output = new EditTodoTeamOutputData("Task Successfully Edited!");
            team.getTeamTasks().remove(index);
            team.getTeamTasks().add(newTodo);
            team.setTeamTasks(team.getTeamTasks());
            dataAccessInterface.updateTeam(team);
            EntityMemory.setLoggedInUser(user);
            this.editTodoTeamPresenter.prepareSuccessView(output);
        }
    }
}
