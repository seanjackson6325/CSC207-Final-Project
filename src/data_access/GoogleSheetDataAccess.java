package data_access;

import entity.Team;
import entity.Todo;
import entity.User;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GoogleSheetDataAccess implements GoogleSheetDataAccessInterface {

    private final String key = "AIzaSyD6-IWNU0lZEDea6qlLdBgIP2k7rCtGfVc";
    private final String spreadsheetId = "1hDLCHjcF-QNP7E9hu2O1L3foRHLhZMkirfVefCyxtFc";
    private final String clientID = "624377237022-8rrpocmm6in8ht8qjkki0tlhrgl6eusu.apps.googleusercontent.com";
    private final String clinetSecret = "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";
    //https://accounts.google.com/o/oauth2/auth/oauthchooseaccount?access_type=offline&approval_prompt=auto&client_id=[YOUR CLIENT ID HERE]&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fspreadsheets&redirect_uri=http%3A%2F%2Flocalhost&service=lso&o2v=1&theme=glif&flowName=GeneralOAuthFlow
    private final String accessCode = "4/0AfJohXn6lWroj4_6SdHNQf9QIB4rz1JNle0csBjXRLSGuEySUVI68XSTKXSCQ3UrMnBsxA";
    private final String refresh_token = "1//05mKc0v9JzEdpCgYIARAAGAUSNwF-L9IrC7XGlJx88NL3NvEEM2wu21p8u3WGQa8AA42AY_DUlPxvgPj7Yp8NpUpgeKMGl0A4HbI";
    private String access_token = "";
    private String token_type = "";

    public GoogleSheetDataAccess() {
        // sets up the access_token that will be used by the Class
        RefreshAccessToken();
    }

    public static void main(String[] args) throws IOException {
        GoogleSheetDataAccess da = new GoogleSheetDataAccess();
        //User TestUser = da.readUser("Test User");
//        Team TestTeam = da.readTeam("Test Team");
//        if (TestUser == null) {
//            System.out.println("null");
//        }
//        else {
//            System.out.println(TestUser.getTaskList());
//            System.out.println(TestTeam.getTeamName());
//            System.out.println(TestTeam.getTeamTasks());
//            System.out.println(TestTeam.getManagers());
//            System.out.println(TestTeam.getMembers());
//        }


        System.out.println(da.CreateSheet("User_", "asdf").body().string());

    }

    /**
     * Refreshes the Access Token needed to edit
     */
    private void RefreshAccessToken() {
        String url = "https://accounts.google.com/o/oauth2/token";

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Build the request body
        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "refresh_token")
                .add("refresh_token", this.refresh_token)
                .add("client_id", this.clientID)
                .add("client_secret", this.clinetSecret)
                .build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        try {
            JSONObject response = new JSONObject(client.newCall(request).execute().body().string());
            this.access_token = response.getString("access_token");
            this.token_type = response.getString("token_type");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * System.out.println(RefreshToken)
     * This method is used to get a new Refresh Token if needed (for bug fix use) (and initial call)
     */
    private void GetRefreshToken() {
        String url = "https://accounts.google.com/o/oauth2/token";

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Build the request body
        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "authorization_code")
                .add("code", this.accessCode)
                .add("client_id", this.clientID)
                .add("client_secret", this.clinetSecret)
                .add("scope", "https://www.googleapis.com/auth/spreadsheets")
                .add("redirect_uri", "http://localhost")
                .build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Response CreateSheet(String type, String sheetName) {

            //TODO
        String url = "https://sheets.googleapis.com/v4/spreadsheets/" + spreadsheetId +":batchUpdate";

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Build the request body
        RequestBody requestBody = new FormBody.Builder()
                .add("Authorization", this.token_type + " " + this.access_token)
                .add("requests", "[{\"addSheet\":{\"properties\":{\"title\":\"" + type + sheetName + "\"}}}]")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            return client.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Response RequestSheet(String sheetName, String range) {

        String url = "https://sheets.googleapis.com/v4/spreadsheets/"+ this.spreadsheetId + "/values/" + sheetName + "!" + range + "?key=" + this.key;

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        try {
            return client.newCall(request).execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Todo convertStringtoTodo(String string) {
        String[] toDoAttr = string.split(",");
        return new Todo(
                toDoAttr[0],
                toDoAttr[1],
                LocalDateTime.parse(toDoAttr[2]),
                LocalDateTime.parse(toDoAttr[3]),
                toDoAttr[4],
                toDoAttr[5],
                Boolean.parseBoolean(toDoAttr[6])
        );
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public User readUser(String username) {
        //TODO

        try {
            // Create a response for the password (in the first row/column) and record the data
            Response psResponse = RequestSheet("User_" + username, "1:1");
            assert psResponse.body() != null;

            // Checks for Error 400; username does not exist
            if (psResponse.code() == 400) {
                return null;
            }

            JSONObject psJSON = new JSONObject(psResponse.body().string());
            System.out.println(psJSON.toString());
            String password = psJSON.getJSONArray("values").getJSONArray(0).getString(0);

            // Create a response for the list of To-dos, (in the second row) and record the data
            Response toDOResponse = RequestSheet("User_" + username, "2:2");
            assert toDOResponse.body() != null;
            JSONObject toDOJSON = new JSONObject(toDOResponse.body().string());
            List<String> toDO = new ArrayList<String>();
            for (Object s: toDOJSON.getJSONArray("values").getJSONArray(0)) {
                toDO.add(s.toString());
            }

            // Create a response for the list of To-dos, (in the third row) and record the data
            Response teamResponse = RequestSheet("User_" + username, "3:3");
            assert teamResponse.body() != null;
            JSONObject teamJSON = new JSONObject(teamResponse.body().string());
            List<String> teams = new ArrayList<String>();
            for (Object s: teamJSON.getJSONArray("values").getJSONArray(0)) {
                toDO.add(s.toString());
            }

            List<Todo> taskList = new ArrayList<Todo>();
            // Convert the to-do Strings into to-do classes
            for (String s: toDO) {
                taskList.add(convertStringtoTodo(s));
            }

            return new User(username, password, taskList, teams);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {
        deleteUser(user.getUsername());
        createUser(user);
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void createTeam(Team team) {

    }

    @Override
    public Team readTeam(String teamName) {
        try {
            // Create a response for the team tasks (in the first row) and record the data
            Response ttResponse = RequestSheet("Team_" + teamName, "1:1");
            assert ttResponse.body() != null;

            // Checks for Error 400; username does not exist
            if (ttResponse.code() == 400) {
                return null;
            }

            String[] ttValue = ttResponse.body().string().replaceAll("\n", "").split("\"");
            List<String> teamTasks = new ArrayList<String>();
            int k = 11;
            while (k < ttValue.length) {
                teamTasks.add(ttValue[k]);
                k += 2;
            }

            // Create a response for the list of managers, (in the second row) and record the data
            Response managerResponse = RequestSheet("Team_" + teamName, "2:2");
            assert managerResponse.body() != null;
            String[] managerValue = managerResponse.body().string().replaceAll("\n", "").split("\"");
            List<String> managers = new ArrayList<String>();
            int j = 11;
            while (j < managerValue.length) {
                managers.add(managerValue[j]);
                j += 2;
            }

            // Create a response for the list of members, (in the third row) and record the data
            Response memberResponse = RequestSheet("Team_" + teamName, "3:3");
            assert memberResponse.body() != null;
            String[] memberValue = memberResponse.body().string().replaceAll("\n", "").split("\"");
            List<String> members = new ArrayList<String>();
            int i = 11;
            while (i < memberValue.length) {
                members.add(memberValue[i]);
                i += 2;
            }

            List<Todo> taskList = new ArrayList<Todo>();
            // Convert the to-do Strings into to-do classes
            for (String s: teamTasks) {
                taskList.add(convertStringtoTodo(s));
            }

            return new Team(teamName, taskList, managers, members);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }

    @Override
    public void updateTeam(Team team) {
        deleteTeam(team.getTeamName());
        createTeam(team);
    }

    @Override
    public void deleteTeam(String teamName) {

    }
}
