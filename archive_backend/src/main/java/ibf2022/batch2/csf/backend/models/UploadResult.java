package ibf2022.batch2.csf.backend.models;
import java.io.Serializable;
import org.springframework.web.multipart.MultipartFile;

public class UploadResult implements Serializable{
    private String name;
    private String title;
    private String comments;
    private MultipartFile archive;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public MultipartFile getArchive() {
        return archive;
    }
    public void setArchive(MultipartFile archive) {
        this.archive = archive;
    }
 
 
    public UploadResult(String name, String title, String comments, MultipartFile archive) {
        this.name = name;
        this.title = title;
        this.comments = comments;
        this.archive = archive;
    }
    public UploadResult() {
    }

    public UploadResult(String name, String title, MultipartFile archive) {
        this.name = name;
        this.title = title;
        this.archive = archive;
    }
    
}
