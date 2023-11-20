package use_case.CreateTeam;

public interface CreateTeamOutputBoundary {

    void prepareSuccessView(CreateTeamOutputData createTeamOutputData);

    void prepareFailView(String error);
}
