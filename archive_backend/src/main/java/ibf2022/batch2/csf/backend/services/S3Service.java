package ibf2022.batch2.csf.backend.services;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Service
public class S3Service {
    
    @Autowired
    ImageRepository imgRepo;

    public String upload(ibf2022.batch2.csf.backend.models.UploadResult ur) throws IOException{
        return imgRepo.upload(ur);
    }
    
}
