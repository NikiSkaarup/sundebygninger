package model;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Image extends File {

    private String path = "/image/" + this.getId();

    public Image() {
    }

    /***
     * Initialize with ID in order to have a file with an ID

     * @param id the id of the file
     */
    public Image(int id) {
        super(id);
    }

    public FileType getType() {
        int ftId = 2;
        if (super.getType().getId() != ftId) {
            FileType ft = new FileType(ftId);
            ft.setName(super.getType().getName());
            super.setType(ft);
        }
        return super.getType();
    }

    public String getPath() {
        return path;
    }
}
