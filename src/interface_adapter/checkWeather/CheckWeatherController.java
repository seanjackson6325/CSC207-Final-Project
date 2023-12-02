package interface_adapter.checkWeather;

import use_case.CheckWeather.CheckWeatherInputBoundary;

public class CheckWeatherController {

    private CheckWeatherInputBoundary checkWeatherInteractor;

    public CheckWeatherController(CheckWeatherInputBoundary checkWeatherInteractor)
    {
        this.checkWeatherInteractor = checkWeatherInteractor;
    }

    public void execute()
    {
        checkWeatherInteractor.execute();
    }

}
