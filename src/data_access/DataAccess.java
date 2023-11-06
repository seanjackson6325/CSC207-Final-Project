package data_access;

import entity.Team;
import entity.Todo;
import entity.User;
import okhttp3.*;
import org.json.JSONMLParserConfiguration;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class DataAccess implements DataAccessInterface {

    private final String dataPath = "src/data_access/data.json";
    private JSONObject data;

    public DataAccess() {

        JSONMLParserConfiguration


        try {
            System.out.println(new BufferedReader(new FileReader(dataPath)).readLine());
            data = new JSONObject(new FileReader(dataPath).toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        DataAccess da = new DataAccess();
        System.out.println(da.toString());
    }

    /**
     * @param type: is either User_ or Team_
     * @param sheetName: precondition (name does not already exist)
     */
    private Response CreateSheet(String type, String sheetName) {

        return null;
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

        try {
            FileWriter file = new FileWriter(dataPath);
            file.write(data.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User readUser(String username) {

//        try {
//
//            String password = psJSON.getJSONArray("values").getJSONArray(0).getString(0);
//
//            // Create a response for the list of To-dos, (in the second row) and record the data
//            Response toDOResponse = RequestSheet("User_" + username, "2:2");
//            assert toDOResponse.body() != null;
//            JSONObject toDOJSON = new JSONObject(toDOResponse.body().string());
//            List<String> toDO = new ArrayList<String>();
//            for (Object s: toDOJSON.getJSONArray("values").getJSONArray(0)) {
//                toDO.add(s.toString());
//            }
//
//            // Create a response for the list of To-dos, (in the third row) and record the data
//            Response teamResponse = RequestSheet("User_" + username, "3:3");
//            assert teamResponse.body() != null;
//            JSONObject teamJSON = new JSONObject(teamResponse.body().string());
//            List<String> teams = new ArrayList<String>();
//            for (Object s: teamJSON.getJSONArray("values").getJSONArray(0)) {
//                toDO.add(s.toString());
//            }
//
//            List<Todo> taskList = new ArrayList<Todo>();
//            // Convert the to-do Strings into to-do classes
//            for (String s: toDO) {
//                taskList.add(convertStringtoTodo(s));
//            }
//
//            return new User(username, password, taskList, teams);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
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
