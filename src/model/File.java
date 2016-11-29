package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class File {
    private int id;
    private String name;
    private byte[] data;
    private Building building;
    private FileType type;
    private String path;

    public File() {
        id=-1;
        name = "";
        data = new byte[]{};
        building = new Building();
        type = new FileType();
        path = "";
    }

    /***
     * Initialize with ID in order to have a file with an ID
     * @param id the id of the file
     */
    public File(int id) {
        this.id = id;
    }

    /***
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /***
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /***
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     *
     * @return byte[]
     */
    public byte[] getData() {
        return data;
    }

    /***
     * Set data with a byte[]
     * @param data byte[]
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /***
     * Set data by InputStream and MaxFileSize
     * @param is InputStream in the form of an Image or Document
     * @param size size is the maxFileSize for the upload
     * @throws IOException
     */
    public void setData(InputStream is, int size) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[size];
        while ((nRead = is.read(data, 0, data.length)) != -1)
            buffer.write(data, 0, nRead);
        buffer.flush();
        this.data = buffer.toByteArray();
    }

    /***
     *
     * @return Building
     */
    public Building getBuilding() {
        return building;
    }

    /***
     *
     * @param building Building
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /***
     *
     * @return
     */
    public FileType getType() {
        return type;
    }

    /***
     * Set FileType by FileType
     * @param type FileType
     */
    public void setType(FileType type) {
        this.type = type;
        updatePath();
    }

    private void updatePath() {
        if(type.getId() == 1)
            path = "/document/" + id;
        if(type.getId() == 2)
            path = "/image/" + id;
    }

    /***
     * Set FileType by Int to initialize a new FileType
     * @param id id of the FileType
     */
    public void setType(int id) {
        setType(new FileType(id));
    }

    public String getPath() {
        return path;
    }
}
