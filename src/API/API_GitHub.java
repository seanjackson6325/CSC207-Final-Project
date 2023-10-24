package API;

import okhttp3.*;

import java.io.IOException;

public class API_GitHub implements GitHubInt {

    public static void main(String[] args) {

        // create an instance of API.API_GitHub (as the method isn't static)
        // you can run this psvm to verify access to private api (and your token).
        API_GitHub gh = new API_GitHub();
        String api_url = "https://api.github.com/user/repos";
        String api_token = "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";
        gh.GetUserInfo(api_url, api_token);
    }

    @Override
    public void GetUserInfo(String url, String token) {

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            System.out.println(response.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void GetDiscussion(String url, String token) {
        //TODO valid access to read:discussion

    }

    @Override
    public void GetProject(String url, String token) {
        //TODO valid access to read:project

    }

}
