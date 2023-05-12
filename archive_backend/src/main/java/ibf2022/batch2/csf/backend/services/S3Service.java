package ibf2022.batch2.csf.backend.services;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import ibf2022.batch2.csf.backend.models.UploadResult;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;

@Service
public class S3Service {
    
    @Autowired
    private AmazonS3 s3Client;
    
    @Autowired
    ImageRepository imgRepo;

    @Value("csf-assessment-aisvarya")
    private String bucketName;

    public String upload(UploadResult ur) throws IOException{
            File destDir = new File("./src/main/resources");
            byte[] buffer = new byte[1024];
            List<String> filesReceived = new ArrayList<>();
            ZipInputStream zis = new ZipInputStream(ur.getArchive().getInputStream());
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                System.out.println(zipEntry.getName());
                filesReceived.add(fileName);
                File newFile = new File(destDir, zipEntry.getName());
                newFile.getParentFile().mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                zipEntry = zis.getNextEntry();
            }
            
            zis.close();
               
             File toUpload = destDir;
    
             File[] photos = toUpload.listFiles();
    
        Map<String, String> userData = new HashMap<>();
        userData.put("name", ur.getName());
        userData.put("uploadDateTime", LocalDateTime.now().toString());
        userData.put("title", ur.getTitle());
        userData.put("comments",ur.getComments());

        for(File file: photos){
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(ur.getArchive().getContentType());
            metadata.setContentLength(file.length());
            metadata.setUserMetadata(userData);
            FileInputStream fis2 = new FileInputStream(file);

            PutObjectRequest putRequest = new PutObjectRequest(bucketName,file.getName(), fis2 , metadata);
            putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(putRequest);
        }
        return null;
    }

    
}
