package model;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Document extends File {

    private String path = "/document/" + this.getId();

    public Document() {
    }

    /***
     * Initialize with ID in order to have a file with an ID

     * @param id the id of the file
     */
    public Document(int id) {
        super(id);
    }

    public FileType getType() {
        int ftId = 1;
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
