package com.onquantum.rockstar.methods;

//import com.sun.javafx.binding.StringFormatter;

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
 * Created by Admin on 7/16/15.
 */
@WebServlet("/get_sound_pack_icon")
public class GetSoundPackIcon extends HttpServlet {

    private static String bucketName = "onquantum";
    //private static String RELATIVE_PATH = "/files/sound_packs_icon/";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String fileName = request.getParameter("file_name");
        if(fileName == null)
            return;

        AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();

        S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, "rockstar/sound_packs_icon/" + fileName));

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

        String fileName = request.getParameter("file_name");
        if(fileName == null)
            return;

        AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();

        S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, "rockstar/sound_packs_icon/" + fileName));

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
