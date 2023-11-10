package data_access;

import org.junit.Test;
import java.io.*;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ImageKitIoAPITest {

    private final String dataPath = "test/data_access/APItest.json";
    private final String fileName = "APItest.json";
    private final ImageKitIoAPI IKI = new ImageKitIoAPI();
    private final String TestJson;


    public ImageKitIoAPITest() {
        String time = LocalDateTime.now().toString();
        TestJson = ("{\"test\": \"" + time + "\"}");
    }


    @Test
    public void testUpload() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(dataPath));
            bw.write(TestJson);
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // runs and doesn't return an exception
        IKI.upload(dataPath, fileName);
    }


    @Test
    public void testDownload() {
        // change contents of APItest.json
        String fileJson;
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataPath));
            fileJson = br.readLine();

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
            assertEquals(br.readLine(), fileJson);
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
