package com.onquantum.rockstar.methods;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.onquantum.rockstar.common.AWSHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Admin on 7/24/15.
 */
@WebServlet("/get_sound_file")
public class GetSoundFile extends HttpServlet {


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String soundPack = request.getParameter("sound_pack");
        String fileName = request.getParameter("file_name");
        if(soundPack == null || fileName == null) {
            return;
        }

        AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();

        S3Object s3object = s3Client.getObject(new GetObjectRequest(AWSHelper.bucketName, "rockstar/" + soundPack + "/" + fileName));

        response.setContentType(s3object.getObjectMetadata().getContentType());
        response.setContentLength((int)s3object.getObjectMetadata().getContentLength());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=" + fileName);
        response.setHeader(headerKey,headerValue);

        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int byteRead = -1;
        while ((byteRead = s3object.getObjectContent().read(buffer)) != -1) {
            outputStream.write(buffer, 0, byteRead);
        }
        outputStream.close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String soundPack = request.getParameter("sound_pack");
        String fileName = request.getParameter("file_name");
        if(soundPack == null || fileName == null) {
            return;
        }

        AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();

        S3Object s3object = s3Client.getObject(new GetObjectRequest(AWSHelper.bucketName, "rockstar/" + soundPack + "/" + fileName));

        response.setContentType(s3object.getObjectMetadata().getContentType());
        response.setContentLength((int)s3object.getObjectMetadata().getContentLength());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=" + fileName);
        response.setHeader(headerKey,headerValue);

        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int byteRead = -1;
        while ((byteRead = s3object.getObjectContent().read(buffer)) != -1) {
            outputStream.write(buffer, 0, byteRead);
        }
        outputStream.close();
    }
}
