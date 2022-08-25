package com.example.demo.service.impl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static com.example.demo.FileUploader.ABSOLUTE_FILE_PATH;

@WebServlet("/download/file")
public class DownloadFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setHeader("Content-disposition", "attachment/txt");

        File file = new File(ABSOLUTE_FILE_PATH);
        OutputStream out = resp.getOutputStream();
        FileInputStream input = new FileInputStream(file);

        byte[] buffer = new byte[4096];
        int length;

        while ((length = input.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        input.close();
        out.flush();


    }
}
