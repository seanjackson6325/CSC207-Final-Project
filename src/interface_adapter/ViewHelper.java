package interface_adapter;

import data_access.DataAccess;
import entity.Team;
import weather_API.WeatherAPI;

public class ViewHelper {

    // This class is made to bypass CA/SOLID, to implement some necessary use cases
    // since our group already is responsible for enough use cases, but these are needed for the functionality.

    public Team getTeamByName(String teamName) {
        DataAccess dataAccess = new DataAccess();
        return dataAccess.readTeam(teamName);
    }

    public String getWeatherInfo() {
        WeatherAPI weatherAPI = new WeatherAPI();
        return weatherAPI.getWeather();
    }

}
