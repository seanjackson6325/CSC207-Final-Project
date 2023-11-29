package use_case.CheckWeather;

import use_case.CreateTeam.CreateTeamOutputData;

public interface CheckWeatherOutputBoundary {

    void prepareSuccessView(CheckWeatherOutputData checkWeatherOutputData);

    void prepareFailView(String error);

}
