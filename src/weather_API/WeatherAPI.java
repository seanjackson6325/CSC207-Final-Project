package weather_API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.time.Instant;

public class WeatherAPI {

    private final String apiKey = "e76bf39d6d8a7ea676663caff3c3601a";

    public String getWeather() {

        String url = "https://api.openweathermap.org/data/3.0/onecall?" +
                "lat=43.65" +
                "&lon=-79.38" +
                "&units=metric" +
                "&appid=" + apiKey;

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.code() == 200;

            Gson gson = new Gson();
            JsonObject data = gson.fromJson(response.body().string(), JsonObject.class);
            JsonObject weatherInfo = data.get("current").getAsJsonObject();

            return "TimeZone: " + data.get("timezone").getAsString() + " \n"
                    + "Temperature: " + weatherInfo.get("temp") + " degrees centigrade\n"
                    + "Feels Like: " + weatherInfo.get("feels_like") + " degrees centigrade\n"
                    + data.get("daily").getAsJsonArray().get(0).getAsJsonObject().get("summary").toString().replace("\"", "");

        } catch (IOException e) {
            return null;
        }
        //
    }
}
