package use_case.CompleteTodoTeam;

import interface_adapter.createTeam.TeamViewModel;
import use_case.DeleteTodoTeam.DeleteTodoTeamOutputData;
import view.ViewManager;

public interface CompleteTodoTeamOutputBoundary {

    void prepareSuccessView(CompleteTodoTeamOutputData output);

    void prepareFailView(String error);

}
