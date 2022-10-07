package s3task;
import java.io.File;

import com.amazonaws.AmazonServiceException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


public class ObjectUpload {

	public static void main(String[] args) {
		final AmazonS3 s3obj = AmazonS3ClientBuilder.defaultClient();
		String bucket_name = "bucket-sample-22";
		String key_name="demo.mp4";
		String file_path="C:\\Users\\kavya.s\\Downloads\\Amazon_S3.mp4";
	  
		try
		{
			System.out.println("Uploading");
			s3obj.putObject(bucket_name,key_name,new File(file_path));
		}
		catch(AmazonServiceException e)
		{
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}
	}

}
