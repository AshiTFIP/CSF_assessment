package ibf2022.batch2.csf.backend.repositories;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ibf2022.batch2.csf.backend.models.UploadResult;
import ibf2022.batch2.csf.backend.services.S3Service;

@Repository
public class ImageRepository {

	@Autowired
	S3Service s3Svc;

	public String upload(UploadResult ur) throws IOException{
		return s3Svc.upload(ur);
	}
}
