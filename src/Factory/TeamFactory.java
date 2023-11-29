package Factory;

import data_access.DataAccessInterface;
import entity.Todo;
import entity.Team;
import interface_adapter.addMember.AddMemberController;
import interface_adapter.addMember.AddMemberPresenter;
import interface_adapter.createTeam.CreateTeamController;
import interface_adapter.createTeam.CreateTeamPresenter;
import interface_adapter.createTeam.TeamViewModel;
import interface_adapter.createTodoTeam.CreateTodoTeamController;
import interface_adapter.createTodoTeam.CreateTodoTeamPresenter;
import interface_adapter.removeMember.RemoveMemberController;
import interface_adapter.removeMember.RemoveMemberPresenter;
import interface_adapter.user.UserViewModel;
import use_case.AddMember.AddMemberInteractor;
import use_case.AddMember.AddMemberOutputBoundary;
import use_case.CreateTeam.CreateTeamInteractor;
import use_case.CreateTeam.CreateTeamOutputBoundary;
import use_case.CreateTodoTeam.CreateTodoTeamInteractor;
import use_case.CreateTodoTeam.CreateTodoTeamOutputBoundary;
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
        return new TeamView(teamViewModel,
                createTeamController,
                addMemberController,
                removeMemberController,
                createTodoTeamController);
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


}
