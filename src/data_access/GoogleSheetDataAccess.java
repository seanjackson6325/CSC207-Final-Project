package data_access;

import entity.Team;
import entity.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GoogleSheetDataAccess implements GoogleSheetDataAccessInterface {

    public static void main(String[] args) {
        APIcall();
    }

    private static void APIcall() {

        //https://sheets.googleapis.com/v4/spreadsheets/PUT YOUR SHEET
        // ID HERE/values/PUT YOUR SHEET NAME HERE (sheet name not spreadsheet name)?key=PUT YOUR API KEY HERE
        String key = "AIzaSyD6-IWNU0lZEDea6qlLdBgIP2k7rCtGfVc";
        String id = "1hDLCHjcF-QNP7E9hu2O1L3foRHLhZMkirfVefCyxtFc";
        String url = "https://sheets.googleapis.com/v4/spreadsheets/"+id+"/values/Sheet1?key=" + key;

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .get()
                .url(url)
                //.addHeader("key", key)
                .build();

        try {
            System.out.println(request);
            Response response = client.newCall(request).execute();
            System.out.println(response);
            System.out.println(response.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public User readUser(String username) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void createTeam(Team team) {

    }

    @Override
    public Team readTeam(String teamName) {
        return null;
    }

    @Override
    public void updateTeam(Team team) {

    }

    @Override
    public void deleteTeam(String teamName) {

    }
}
