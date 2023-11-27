package Factory;

import data_access.DataAccessInterface;
import entity.Todo;
import entity.Team;
import interface_adapter.createTeam.CreateTeamController;
import interface_adapter.createTeam.CreateTeamPresenter;
import interface_adapter.createTeam.TeamViewModel;
import interface_adapter.user.UserViewModel;
import use_case.CreateTeam.CreateTeamInteractor;
import use_case.CreateTeam.CreateTeamOutputBoundary;
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
        return new TeamView(teamViewModel, createTeamController);
    }

    public static CreateTeamController createTeamController(ViewManager viewManager,
                                                            TeamViewModel teamViewModel,
                                                            DataAccessInterface dataAccessInterface)
    {
        CreateTeamOutputBoundary createTeamPresenter = new CreateTeamPresenter(viewManager, teamViewModel);
        CreateTeamInteractor createTeamInteractor = new CreateTeamInteractor(dataAccessInterface, createTeamPresenter);
        return new CreateTeamController(createTeamInteractor);
    }

}
