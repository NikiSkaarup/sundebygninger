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

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setData(InputStream is, int size) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[size];
        while ((nRead = is.read(data, 0, data.length)) != -1)
            buffer.write(data, 0, nRead);
        try {
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.data = buffer.toByteArray();
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }
}
