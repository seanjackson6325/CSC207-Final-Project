package Factory;

import data_access.DataAccessInterface;
import entity.Todo;
import entity.Team;
import interface_adapter.addMember.AddMemberController;
import interface_adapter.addMember.AddMemberPresenter;
import interface_adapter.checkWeather.CheckWeatherController;
import interface_adapter.checkWeather.CheckWeatherPresenter;
import interface_adapter.completeTodoTeam.CompleteTodoTeamController;
import interface_adapter.completeTodoTeam.CompleteTodoTeamPresenter;
import interface_adapter.createTeam.CreateTeamController;
import interface_adapter.createTeam.CreateTeamPresenter;
import interface_adapter.createTeam.TeamViewModel;
import interface_adapter.createTodoTeam.CreateTodoTeamController;
import interface_adapter.createTodoTeam.CreateTodoTeamPresenter;
import interface_adapter.deleteTodoTeam.DeleteTodoTeamController;
import interface_adapter.deleteTodoTeam.DeleteTodoTeamPresenter;
import interface_adapter.editTodoTeam.EditTodoTeamController;
import interface_adapter.editTodoTeam.EditTodoTeamPresenter;
import interface_adapter.removeMember.RemoveMemberController;
import interface_adapter.removeMember.RemoveMemberPresenter;
import interface_adapter.user.UserViewModel;
import use_case.AddMember.AddMemberInteractor;
import use_case.AddMember.AddMemberOutputBoundary;
import use_case.CheckWeather.CheckWeatherInteractor;
import use_case.CheckWeather.CheckWeatherOutputBoundary;
import use_case.CompleteTodoTeam.CompleteTodoTeamInteractor;
import use_case.CompleteTodoTeam.CompleteTodoTeamOutputBoundary;
import use_case.CreateTeam.CreateTeamInteractor;
import use_case.CreateTeam.CreateTeamOutputBoundary;
import use_case.CreateTodoTeam.CreateTodoTeamInteractor;
import use_case.CreateTodoTeam.CreateTodoTeamOutputBoundary;
import use_case.DeleteTodoTeam.DeleteTodoTeamInteractor;
import use_case.DeleteTodoTeam.DeleteTodoTeamOutputBoundary;
import use_case.EditTodoTeam.EditTodoTeamInputData;
import use_case.EditTodoTeam.EditTodoTeamInteractor;
import use_case.EditTodoTeam.EditTodoTeamOutputBoundary;
import use_case.RemoveMember.RemoveMemberInteractor;
import use_case.RemoveMember.RemoveMemberOutputBoundary;
import view.ViewManager;
import view.team.TeamView;

import java.util.List;

public class TeamFactory {
    public Team create(String teamName, List<Todo> teamTasks, List<String> managers, List<String> members) {
        return new Team(teamName, teamTasks, managers, members);
    }

    public static TeamView createTeamView(ViewManager viewManager,
                                          TeamViewModel teamViewModel,
                                          DataAccessInterface dataAccessInterface)
    {
        CreateTeamController createTeamController = createTeamController(viewManager, teamViewModel, dataAccessInterface);
        AddMemberController addMemberController = createAddMemberController(viewManager, teamViewModel, dataAccessInterface);
        RemoveMemberController removeMemberController = createRemoveMemberController(viewManager, teamViewModel, dataAccessInterface);
        CreateTodoTeamController createTodoTeamController = createAddTodoTeamController(viewManager, teamViewModel, dataAccessInterface);
        DeleteTodoTeamController deleteTodoTeamController = createDeleteTodoTeamController(viewManager, teamViewModel, dataAccessInterface);
        EditTodoTeamController editTodoTeamController = createEditTodoTeamController(viewManager, teamViewModel, dataAccessInterface);
        CompleteTodoTeamController completeTodoTeamController = createCompleteTodoTeamController(viewManager, teamViewModel, dataAccessInterface);
        CheckWeatherController checkWeatherController = createCheckWeatherController(viewManager);
        return new TeamView(teamViewModel,
                createTeamController,
                addMemberController,
                removeMemberController,
                createTodoTeamController,
                deleteTodoTeamController,
                editTodoTeamController,
                completeTodoTeamController,
                checkWeatherController);
    }

    public static CreateTeamController createTeamController(ViewManager viewManager,
                                                            TeamViewModel teamViewModel,
                                                            DataAccessInterface dataAccessInterface)
    {
        CreateTeamOutputBoundary createTeamPresenter = new CreateTeamPresenter(viewManager, teamViewModel);
        CreateTeamInteractor createTeamInteractor = new CreateTeamInteractor(dataAccessInterface, createTeamPresenter);
        return new CreateTeamController(createTeamInteractor);
    }

    public static AddMemberController createAddMemberController(ViewManager viewManager,
                                                                TeamViewModel teamViewModel,
                                                                DataAccessInterface dataAccessInterface)
    {
        AddMemberOutputBoundary addMemberPresenter = new AddMemberPresenter(viewManager, teamViewModel);
        AddMemberInteractor addMemberInteractor = new AddMemberInteractor(dataAccessInterface, addMemberPresenter);
        return new AddMemberController(addMemberInteractor);
    }

    public static RemoveMemberController createRemoveMemberController(ViewManager viewManager,
                                                                      TeamViewModel teamViewModel,
                                                                      DataAccessInterface dataAccessInterface)
    {
        RemoveMemberOutputBoundary removeMemberPresenter = new RemoveMemberPresenter(viewManager, teamViewModel);
        RemoveMemberInteractor removeMemberInteractor = new RemoveMemberInteractor(dataAccessInterface, removeMemberPresenter);
        return new RemoveMemberController(removeMemberInteractor);
    }

    public static CreateTodoTeamController createAddTodoTeamController(ViewManager viewManager,
                                                                       TeamViewModel teamViewModel,
                                                                       DataAccessInterface dataAccessInterface)
    {
        CreateTodoTeamOutputBoundary createTodoTeamPresenter = new CreateTodoTeamPresenter(viewManager, teamViewModel);
        CreateTodoTeamInteractor createTodoTeamInteractor = new CreateTodoTeamInteractor(dataAccessInterface, createTodoTeamPresenter);
        return new CreateTodoTeamController(createTodoTeamInteractor);
    }

    public static DeleteTodoTeamController createDeleteTodoTeamController(ViewManager viewManager,
                                                                          TeamViewModel teamViewModel,
                                                                          DataAccessInterface dataAccessInterface)
    {
        DeleteTodoTeamOutputBoundary deleteTodoTeamPresenter = new DeleteTodoTeamPresenter(viewManager, teamViewModel);
        DeleteTodoTeamInteractor deleteTodoTeamInteractor = new DeleteTodoTeamInteractor(dataAccessInterface, deleteTodoTeamPresenter);
        return new DeleteTodoTeamController(deleteTodoTeamInteractor);
    }

    public static EditTodoTeamController createEditTodoTeamController(ViewManager viewManager,
                                                                      TeamViewModel teamViewModel,
                                                                      DataAccessInterface dataAccessInterface)
    {
        EditTodoTeamOutputBoundary editTodoTeamPresenter = new EditTodoTeamPresenter(viewManager, teamViewModel);
        EditTodoTeamInteractor editTodoTeamInteractor = new EditTodoTeamInteractor(dataAccessInterface, editTodoTeamPresenter);
        return new EditTodoTeamController(editTodoTeamInteractor);
    }

    public static CompleteTodoTeamController createCompleteTodoTeamController(ViewManager viewManager,
                                                                              TeamViewModel teamViewModel,
                                                                              DataAccessInterface dataAccessInterface)
    {
        CompleteTodoTeamOutputBoundary completeTodoTeamPresenter = new CompleteTodoTeamPresenter(viewManager, teamViewModel);
        CompleteTodoTeamInteractor completeTodoTeamInteractor = new CompleteTodoTeamInteractor(dataAccessInterface, completeTodoTeamPresenter);
        return new CompleteTodoTeamController(completeTodoTeamInteractor);
    }

    public static CheckWeatherController createCheckWeatherController(ViewManager viewManager)
    {
        CheckWeatherOutputBoundary checkWeatherPresenter = new CheckWeatherPresenter(viewManager);
        CheckWeatherInteractor checkWeatherInteractor = new CheckWeatherInteractor(checkWeatherPresenter);
        return new CheckWeatherController(checkWeatherInteractor);
    }

}
