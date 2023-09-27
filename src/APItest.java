import okhttp3.*;

import java.io.IOException;

public class APItest {

    private static String token = "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";

    public static void main(String[] args) {

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://api.github.com/user/repos")
                    .method("GET", body)
                    .addHeader("Authorization", "Bearer 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")
                    .build();
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
