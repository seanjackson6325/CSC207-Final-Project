package data_access;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.exceptions.*;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.ResultFileVersions;
import okhttp3.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


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
        //initialize imageKitAPI
        String urlEndpoint = "https://ik.imagekit.io/kylej143"; //endpoint
        Configuration config = new Configuration(publicKey, privateKey, urlEndpoint);
        ImageKit imageKit = ImageKit.getInstance();
        imageKit.setConfig(config);

        String data;
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            data = br.readLine();
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String encodedData = Base64.getEncoder().encodeToString(data.getBytes());

        FileCreateRequest fileCreateRequest =new FileCreateRequest(encodedData, fileName);
        List<String> responseFields=new ArrayList<>();
        fileCreateRequest.setResponseFields(responseFields);
        fileCreateRequest.setUseUniqueFileName(false);
        try {
            String fileId = ImageKit.getInstance().upload(fileCreateRequest).getFileId();
            ImageKit.getInstance().deleteFile(fileId);
            ImageKit.getInstance().upload(fileCreateRequest);

        } catch (ForbiddenException | TooManyRequestsException | InternalServerException | UnauthorizedException |
                 BadRequestException | UnknownException e) {
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

        //initialize imageKitAPI
        String urlEndpoint = "https://ik.imagekit.io/kylej143"; //endpoint
        Configuration config = new Configuration(publicKey, privateKey, urlEndpoint);
        ImageKit imageKit = ImageKit.getInstance();
        imageKit.setConfig(config);

        FileCreateRequest fileCreateRequest =new FileCreateRequest("e30=", fileName);
        List<String> responseFields=new ArrayList<>();
        fileCreateRequest.setResponseFields(responseFields);
        fileCreateRequest.setUseUniqueFileName(false);
        try {
            String fileId = ImageKit.getInstance().getFileList(fileCreateRequest).getFileId();
            System.out.println(versionID);

            Response response = client.newCall(request).execute();
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            System.out.println(response.body().string());
            bw.write(response.body().string());
            bw.close();

            assert response.code() == 200;

        } catch (ForbiddenException | TooManyRequestsException | InternalServerException | UnauthorizedException |
                 BadRequestException | UnknownException | IOException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
