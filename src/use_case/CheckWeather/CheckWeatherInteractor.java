package use_case.CheckWeather;

import entity.WeatherAPI;

public class CheckWeatherInteractor implements CheckWeatherInputBoundary{

    private CheckWeatherOutputBoundary presenter;

    public CheckWeatherInteractor(CheckWeatherOutputBoundary presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        WeatherAPI weatherAPI = new WeatherAPI();

        String weather = weatherAPI.getWeather();
        if(weather == null)
        {
            presenter.prepareFailView("Failed to obtain weather data. Please check your internet connection.");
        }
        else
        {
            CheckWeatherOutputData output = new CheckWeatherOutputData(weather);
            presenter.prepareSuccessView(output);
        }
    }
}
