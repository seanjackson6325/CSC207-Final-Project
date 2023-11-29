package use_case;

import interface_adapter.checkWeather.CheckWeatherPresenter;
import use_case.CheckWeather.CheckWeatherInteractor;
import use_case.CheckWeather.CheckWeatherOutputBoundary;
import use_case.CheckWeather.CheckWeatherOutputData;
import org.junit.Test;

public class CheckWeatherInteractorTest {

    @Test
    public void testExecute()
    {
        CheckWeatherOutputBoundary checkWeatherPresenter = new CheckWeatherOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckWeatherOutputData checkWeatherOutputData) {
                assert(checkWeatherOutputData.getWeather() != null);
            }

            @Override
            public void prepareFailView(String error) {
                assert(error.equals("Failed to obtain weather data. Please check your internet connection."));
            }
        };

        CheckWeatherInteractor checkWeatherInteractor = new CheckWeatherInteractor(checkWeatherPresenter);
        checkWeatherInteractor.execute();
    }

}
