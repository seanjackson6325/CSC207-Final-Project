package use_case.CheckWeather;

public class CheckWeatherOutputData {

    String weather;

    public CheckWeatherOutputData(String weather)
    {
        this.weather = weather;
    }

    public String getWeather()
    {
        return weather;
    }

}
