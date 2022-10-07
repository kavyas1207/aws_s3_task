package s3task;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class Bucket_creation {

	public static void main(String[] args) {
		final AmazonS3 s3obj = AmazonS3ClientBuilder.defaultClient();
        String bucket_name = "bucket-sample-22";
        try
        { 
        	
            s3obj.createBucket(bucket_name);
        }
        catch(AmazonS3Exception e)
        {
            System.err.println(e.getErrorMessage());
        }
	}

}
