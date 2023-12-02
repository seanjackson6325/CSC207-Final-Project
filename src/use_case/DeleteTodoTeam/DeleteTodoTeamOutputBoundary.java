package use_case.DeleteTodoTeam;

public interface DeleteTodoTeamOutputBoundary {

    void prepareSuccessView(DeleteTodoTeamOutputData output);

    void prepareFailView(String error);

}
