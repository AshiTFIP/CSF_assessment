package ibf2022.batch2.csf.backend.controllers;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ibf2022.batch2.csf.backend.models.UploadResult;
import ibf2022.batch2.csf.backend.repositories.ImageRepository;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping("/")
public class UploadController {

	// @Autowired
	// MongoTemplate template;

	@Autowired
	ImageRepository imgRepo;

	// TODO: Task 2, Task 3, Task 4
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<String> upload(
		@RequestPart("archive") MultipartFile file, @RequestParam("name") String name, @RequestParam("title") String title, @RequestParam("comments") String comments) throws IOException{
		String success = "";
		UploadResult ur = new UploadResult(name, title, comments, file);
        try{
            success = imgRepo.upload(ur);
        } catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        JsonObject payload = Json.createObjectBuilder().add("bundleId", "success").build();

        return ResponseEntity.ok(payload.toString());
	}
	// TODO: Task 5
	

	// TODO: Task 6

}
