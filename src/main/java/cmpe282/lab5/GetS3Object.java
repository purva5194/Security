package cmpe282.lab5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class GetS3Object {
	private static String bucketName = "282lab5";
	private static String key = "hello.txt";
	//private static String access_key = "AKIAJMX7VALJNUW5NS3Q";
	//private static String secret_key = "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";

	public static void main(String[] args) throws IOException
	{
		//BasicAWSCredentials creds = new BasicAWSCredentials(access_key, secret_key); 
		//AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds)).build();
		AmazonS3 s3client = AmazonS3ClientBuilder.defaultClient();
		try {
			System.out.println("Downloading an object");
			S3Object s3object = s3client.getObject(
					new GetObjectRequest(bucketName, key));
			displayTextInputStream(s3object.getObjectContent());
		}
		catch(AmazonServiceException ase) {
			System.err.println("Exception was thrown by the service");
		}
		catch(AmazonClientException ace) {
			System.err.println("Exception was thrown by the client");
		}
	}

	private static void displayTextInputStream(InputStream input) throws IOException
	{
		// Read one text line at a time and display.
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while(true)
		{
			String line = reader.readLine();
			if(line == null) break;
			System.out.println( "    " + line );
		}
		System.out.println();
	}
}