package use_case.CreateTodoTeam;

import use_case.CreateTodoUser.CreateTodoUserOutputData;

public interface CreateTodoTeamOutputBoundary {
    void successView(CreateTodoTeamOutputData outputData);
    void failureView(CreateTodoTeamOutputData outputData);
}