package weather_API;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class WeatherAPITest {

    private final WeatherAPI weatherAPI;

    public WeatherAPITest() {
        weatherAPI = new WeatherAPI();
    }

    @Test
    public void testGetWeather() {
        String weather = weatherAPI.getWeather();
        assertNotEquals(weather, "Failed to Get Weather");
        System.out.println(weather);
    }
}
