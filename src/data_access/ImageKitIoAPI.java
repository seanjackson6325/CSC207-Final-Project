package data_access;

import okhttp3.*;

import java.io.*;
import java.time.Instant;
import java.util.Base64;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;


public class ImageKitIoAPI {
    private final String dataPath = "src/data_access/data.json";
    private final String publicKey = "public_IEPr3LO80N8Dnqhr0fGSEE3+6+4=";
    private final String privateKey = "private_gbpWgMKkbdD0wqV1iYfl178UggM=";
    private final String fileName = "data.json";


    /**
     * Limited to 1 call per second
     * Uploads data.json into ImageKit.io's drive
     */
    public void upload(String dataPath, String fileName) {
        String url = "https://upload.imagekit.io/api/v1/files/upload"; //endpoint
        //client side unix expiry date / unique token : +50 minutes from now
        long unixTimeExpiry = Instant.now().getEpochSecond() + 3000;
        //client signature encoded with HMAC_SHA1 algorithm
        String signature = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, privateKey).hmacHex(
                String.valueOf(unixTimeExpiry) + String.valueOf(unixTimeExpiry));

        String data;

        try {
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            data = br.readLine();
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String encodedData = Base64.getEncoder().encodeToString(data.getBytes());

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        RequestBody requestBodyDelete = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("publicKey", publicKey)
                .addFormDataPart("PrivateKey", privateKey)
                .build();
        Request requestDelete = new Request.Builder()
                .url("https://api.imagekit.io/v1/files/:" + dataPath)
                .post(requestBodyDelete)
                .build();
        try {
            Response response = client.newCall(requestDelete).execute();
            System.out.println(response.body().string());
            assert response.code() == 200;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", encodedData)
                .addFormDataPart("publicKey", publicKey)
                .addFormDataPart("signature", signature)
                .addFormDataPart("expire", String.valueOf(unixTimeExpiry))
                .addFormDataPart("token", String.valueOf(unixTimeExpiry))
                .addFormDataPart("fileName", fileName)
                .addFormDataPart("useUniqueFileName", "false") // replace file        // Build the request
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            assert response.code() == 200;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Limited to 1 call per second
     * Downloads data.json from ImageKit.io's drive
     */
    public void download(String dataPath, String fileName) {
        String url = "https://ik.imagekit.io/kylej143/" + fileName; //endpoint

        // make an instance of OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            bw.write(response.body().string());
            bw.close();

            assert response.code() == 200;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
