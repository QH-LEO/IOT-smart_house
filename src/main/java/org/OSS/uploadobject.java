package org.OSS;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
//二次开发真爽！！！！！！！！
//do whatever I want to do!!!!!!!!
public class uploadobject {

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIPTVKDnbteHJn";
    private static String accessKeySecret = "Hm5O1SSCPlcD73lo4KaedM6tLt1Nk5";

    private static String bucketName = "ailife";
  //  private static String key = "/home/qhwd/smartlife/01.jpg";

    public String upload(String path){
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        String A=dateFormat.format(date);

        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {

            /**
             * Note that there are two ways of uploading an object to your bucket, the one
             * by specifying an input stream as content source, the other by specifying a file.
             */

//            /*
//             * Upload an object to your bucket from an input stream
//             */
//            System.out.println("Uploading a new object to OSS from an input stream\n");
//            String content = "Thank you for using Aliyun Object Storage Service";
//            client.putObject(bucketName, key, new ByteArrayInputStream(content.getBytes()));
//
//            /*
//             * Upload an object to your bucket from a file
//             */
            System.out.println("Uploading a new object to OSS from a file\n");
            client.putObject(new PutObjectRequest(bucketName, A, new File(path)));

            /*
             * Download an object from your bucket
//             */
//            System.out.println("Downloading an object");
//            OSSObject object = client.getObject(new GetObjectRequest(bucketName, key));
//            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
//            displayTextInputStream(object.getObjectContent());

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            client.shutdown();
        }
      //  System.out.println(A);
        return A;
    }

//    private static File createSampleFile() throws IOException {
//        File file = File.createTempFile("oss-java-sdk-", ".txt");
//        file.deleteOnExit();
//
//        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
//        writer.write("abcdefghijklmnopqrstuvwxyz\n");
//        writer.write("0123456789011234567890\n");
//        writer.close();
//
//        return file;
//    }
//
//    private static void displayTextInputStream(InputStream input) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//        while (true) {
//            String line = reader.readLine();
//            if (line == null) break;
//
//            System.out.println("\t" + line);
//        }
//        System.out.println();
//
//        reader.close();
//    }

}