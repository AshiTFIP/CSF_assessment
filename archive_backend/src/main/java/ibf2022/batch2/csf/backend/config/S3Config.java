package ibf2022.batch2.csf.backend.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {
    @Value("DO00LCB6BQ884TNEVQKY")
    private String accessKey; 

    @Value("Gq0t29LJaeXUdhU8+uL905rQPCP1lMzG8c+hE9cKJyo")
    private String secretKey; 

    @Value("https://csf-assessment-aisvarya.sgp1.digitaloceanspaces.com")
    private String endPoint; 
 
    @Value("SGP1")
    private String endPointRegion;

    @Bean
    public AmazonS3 createS3Client(){
        BasicAWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);
        EndpointConfiguration ep = new EndpointConfiguration(endPoint, endPointRegion);

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(ep)
                .withCredentials(new AWSStaticCredentialsProvider(cred))
                .build();
    }
   
    
}
