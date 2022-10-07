package s3task;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import java.io.File;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
public class UploadFile {
	public static long totalBytesTransferred=0;
	
	public static void main(String[] args) {
		String bucket_name = "bucket-sample-22";
		String key_name="demo.mp4";
		String file_path="C:\\Users\\kavya.s\\Downloads\\Amazon_S3.mp4";
		TransferManager tm=null;
	try {
		AmazonS3 s3Client=AmazonS3ClientBuilder.defaultClient();

		tm = TransferManagerBuilder.standard()
  				.withS3Client(s3Client)
  				.withMultipartUploadThreshold((long) (5 * 1024 * 1025))
  				.build();
	
		File video_file=new File(file_path);
		// size of a file (in bytes)
		final long filesize_bytes=video_file.length();
		Upload upload = tm.upload(bucket_name, key_name, video_file);
		
		// ProgressListener progressListener = new ProgressListener(); 
		 upload.addProgressListener(new ProgressListener() {
				public void progressChanged(ProgressEvent progressEvent) {
					totalBytesTransferred+= progressEvent.getBytesTransferred();
			    	
			    	double temp=((double)totalBytesTransferred/filesize_bytes)*100;
			    	
			    	double progress_percentage=Math.round(temp*Math.pow(10,2))/Math.pow(10,2);
			    	double progress_status=0;
			    	
			    	 if (progress_percentage < 100) {
			    		 
			             progress_status = progress_percentage;
			             System.out.println("Progress status:" +progress_status+"%"+"loading");
			             
			           } else if (progress_percentage == 100) {
			        	   
			        	   progress_status = progress_percentage;

			        	   System.out.println("Progress status:" +progress_status+"%"+"success!!");
			           }
				}
	
			});  
		 //Wait block
		     upload.waitForCompletion();
	    	if(upload.isDone()) {
	    		//tm closed
	    		tm.shutdownNow();
	    		System.out.println("File Uploaded successfully!!");
	    	}	 
	}
	catch (Exception e) {
		System.out.println(e.getMessage());
		System.out.println("File Upload Failed");
    	tm.shutdownNow();
	}
	
	}
}
