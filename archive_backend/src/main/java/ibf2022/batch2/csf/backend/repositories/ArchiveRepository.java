package ibf2022.batch2.csf.backend.repositories;
import java.io.IOException;
import java.util.UUID;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import ibf2022.batch2.csf.backend.Utils;
import ibf2022.batch2.csf.backend.models.UploadResult;
public class ArchiveRepository {

	public static final String C_TVSHOWS = "archives";

	@Autowired
	Utils utils;

	@Autowired
	private MongoTemplate template;

	public String recordBundle(UploadResult ur) throws IOException {
		String bundleId = UUID.randomUUID().toString().substring(0, 8);
		Document doc = utils.toDocument(ur, bundleId);
		Document result = template.insert(doc, C_TVSHOWS);
		if(result == null){
			return null;
		}
		else{
			return bundleId;
		}
	}

	//TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundleByBundleId(/* any number of parameters here */) {
		return null;
	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundles(/* any number of parameters here */) {
		return null;
	}


}
