package com.onquantum.rockstar.methods;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.onquantum.rockstar.common.AWSHelper;
import com.onquantum.rockstar.model.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Admin on 5/8/16.
 */
@WebServlet("/download_file")

public class DownloadFile extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/octet-stream charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        int id = 0;

        if (request.getParameter(File.ID) != null) {
            id = Integer.parseInt(request.getParameter(File.ID));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File file = File.GetFileByID(id);
        if (file == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {

            AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();
            S3Object s3object = s3Client.getObject(new GetObjectRequest(AWSHelper.bucketName, file.full_path));

            //String headerKey = "Content-Disposition";
            //String headerValue = String.format("attachment; filename=" + file.file_name);
            //response.setHeader(headerKey,headerValue);
            response.addHeader("file_name",file.file_name);

            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int byteRead = -1;
            while ((byteRead = s3object.getObjectContent().read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteRead);
            }
            outputStream.close();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/octet-stream charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        int id = 0;

        if (request.getParameter(File.ID) != null) {
            id = Integer.parseInt(request.getParameter(File.ID));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File file = File.GetFileByID(id);
        if (file == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {

            AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();
            S3Object s3object = s3Client.getObject(new GetObjectRequest(AWSHelper.bucketName, file.full_path));

            //String headerKey = "Content-Disposition";
            //String headerValue = String.format("attachment; filename=" + file.file_name);
            //response.setHeader(headerKey,headerValue);
            response.addHeader("file_name",file.file_name);

            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int byteRead = -1;
            while ((byteRead = s3object.getObjectContent().read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteRead);
            }
            outputStream.close();
        }
    }
}
