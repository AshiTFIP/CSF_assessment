package ibf2022.batch2.csf.backend;
import java.io.File;
import java.io.IOException;
import org.bson.Document;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import ibf2022.batch2.csf.backend.models.UploadResult;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;

public class Utils {

    @Autowired
    ImageRepository imgRepo;

    public Document toDocument(UploadResult ur, String bundleId) throws IOException {
		Document doc = new Document();
        File[] photos = imgRepo.fileConversion(ur);
        doc.put("bundleId", bundleId);
        doc.put("date", LocalDateTime.now().toString());
        doc.put("title", ur.getTitle());
		doc.put("name", ur.getName());
		doc.put("comments", ur.getComments());
        doc.put("photos", photos);
		return doc;
	}
}
