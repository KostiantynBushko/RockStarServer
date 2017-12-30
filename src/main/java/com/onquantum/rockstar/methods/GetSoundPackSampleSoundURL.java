package com.onquantum.rockstar.methods;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.onquantum.rockstar.common.AWSHelper;
import com.onquantum.rockstar.common.SQLDBConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Admin on 4/24/16.
 * get_sample_sound_url?sound_pack=clean
 */
@WebServlet("/get_sample_sound_url")
public class GetSoundPackSampleSoundURL extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String soundPack = request.getParameter("sound_pack");
        if(soundPack == null) {
            return;
        }

        String query = "SELECT sample_sound FROM guitar_tb WHERE article = '" + soundPack + "'";
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        String fileName = "";

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                fileName = resultSet.getString("sample_sound");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();

        java.util.Date expiration = new java.util.Date();
        long milliSeconds = expiration.getTime();
        milliSeconds += 1000 * 60 * 2; // Add 2 min.
        expiration.setTime(milliSeconds);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(AWSHelper.bucketName, "rockstar/sound_pack_sample/" + fileName);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        PrintWriter pw = response.getWriter();
        pw.write(url.toString());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html charset=cp1251");
        response.setCharacterEncoding("UTF-8");

        String soundPack = request.getParameter("sound_pack");
        if(soundPack == null) {
            return;
        }

        String query = "SELECT sample_sound FROM guitar_tb WHERE article = '" + soundPack + "'";
        Statement statement = SQLDBConnector.getStatement();
        ResultSet resultSet = null;

        String fileName = "";

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                fileName = resultSet.getString("sample_sound");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        AmazonS3 s3Client = AWSHelper.GetAmazonS3Client();

        java.util.Date expiration = new java.util.Date();
        long milliSeconds = expiration.getTime();
        milliSeconds += 1000 * 60 * 2; // Add 2 min.
        expiration.setTime(milliSeconds);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(AWSHelper.bucketName, "rockstar/sound_pack_sample/" + fileName);
        generatePresignedUrlRequest.setMethod(HttpMethod.GET);
        generatePresignedUrlRequest.setExpiration(expiration);
        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

        PrintWriter pw = response.getWriter();
        pw.write(url.toString());
    }
}
