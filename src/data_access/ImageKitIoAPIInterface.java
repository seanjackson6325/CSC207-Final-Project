package data_access;

import entity.User;

public interface ImageKitIoAPIInterface {

    /**
     * uploads the json file to the database
     * @exception RuntimeException if there is an error uploading; if so retry.
     * @param dataPath example: src/data_access/data.json
     * @param fileName example: data.json
     */
    void upload(String dataPath, String fileName);

    /**
     * updates the json file from database
     * @exception RuntimeException if there is an error retrieving; if so retry.
     * @param dataPath example: src/data_access/data.json
     * @param fileName example: data.json
     */
    void download(String dataPath, String fileName);

}
