package com.onquantum.rockstar.methods;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Admin on 12/11/15.
 */
@WebServlet("/s3")
public class GetFileFromS3 extends HttpServlet {

    private static String ACCESS_KEY_ID = "AKIAI7A4MG7MO5R2PNAA";
    private static String SECRET_ACCESS_KEY = "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";

    private static String bucketName = "onquantum";
    //private static String key        = "1.txt"; //"rockstar/distortion/distortion_0_0.ogg";
    private static String key        = "rockstar/distortion/distortion_0_0.ogg";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        AmazonS3 s3Client = new AmazonS3Client(credentials);

        S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, key));

        /*PrintWriter printWriter = response.getWriter();

        printWriter.println("s3Client : " + s3Client.toString());
        printWriter.println("s3Object : " + s3object.toString());
        printWriter.println("s3Object : " + s3object.getKey());
        printWriter.println("s3Object : " + s3object.getObjectMetadata().getContentLength());
        printWriter.println("s3Object : " + s3object.getObjectMetadata().getContentType());
        printWriter.println("s3Object : " + s3object.getObjectMetadata().getRawMetadata().toString());
        printWriter.println("s3Object : " + s3object.getObjectMetadata().getUserMetadata().toString());
        printWriter.close();*/

        response.setContentType(s3object.getObjectMetadata().getContentType());
        response.setContentLength((int)s3object.getObjectMetadata().getContentLength());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=distortion_file");
        response.setHeader(headerKey,headerValue);

        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int byteRead = -1;
        while ((byteRead = s3object.getObjectContent().read(buffer)) != -1) {
            outputStream.write(buffer, 0, byteRead);
        }
        outputStream.close();
    }

    private static String displayTextInputStream(InputStream input)
            throws IOException {
        // Read one text line at a time and display.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

}
