package data_access;

import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.Objects;

public class ImageKitIoAPITest {

    private final String dataPath = "test/data_access/APItest.json";
    private final String fileName = "APItest.json";
    private final ImageKitIoAPI IKI = new ImageKitIoAPI();

    @Before
    public void init() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            bw.write("{\"test\": \"vasdfaasdfasdfasdfasdue\"}");
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testUpload() {
        // runs and doesn't return an exception
        IKI.upload(dataPath, fileName);
    }


    @Test
    public void testDownload() {
        // change contents of APItest.json
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            bw.write("{}");
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // runs and doesn't return an exception
        IKI.download(dataPath, fileName);

        try {
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            assert Objects.equals(br.readLine(), "{\"test\": \"value\"}");
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
