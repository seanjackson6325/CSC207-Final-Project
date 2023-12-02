package use_case.EditTodoTeam;

public interface EditTodoTeamOutputBoundary {

    public void prepareSuccessView(EditTodoTeamOutputData output);

    public void prepareFailView(String error);

}
