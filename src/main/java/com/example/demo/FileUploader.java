package com.example.demo;


import com.example.demo.service.impl.DefaultFileUploader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(name = "fileUploader", value = FileUploader.SERVLET_PATH)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class FileUploader extends HttpServlet {
    public static String ABSOLUTE_FILE_PATH;
    public static final String SERVLET_PATH = "/api/v1/file/upload";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(getHtmlView());
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part file = req.getPart("file");
        processFilePart(file, file.getSubmittedFileName());
        DefaultFileUploader defaultFileUploader = new DefaultFileUploader();
        defaultFileUploader.uploadFile();

        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(getHtmlViewDownload());
        printWriter.close();

    }

    private String getHtmlView() {
        return "<html>\n" +
                "<head>\n" +
                "    <title>File Uploader</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"/api/v1/file/upload\" method=\"post\" name=\"fileUploadForm\" enctype=\"multipart/form-data\">\n" +
                "        <input name=\"file\" type=\"file\">\n" +
                "        <button type=\"submit\">Upload file!</button>\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";
    }

    private String getHtmlViewDownload() {
        return "<html>\n" +
                "<head>\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<a href=\"http://localhost:8080/download/file\" download=\"\">Download file</a>\n" +
                "</body>\n" +
                "</html>";
    }

    private void processFilePart(Part part, String filename) throws IOException {
        int DEFAULT_BUFFER_SIZE = 2048;

        String fileSeparator = "\\";
        ABSOLUTE_FILE_PATH = "E:" + fileSeparator + filename;
        File file = new File(ABSOLUTE_FILE_PATH);
        file.createNewFile();

        InputStream input = null;
        OutputStream output = null;
        try {
            input = new BufferedInputStream(part.getInputStream(), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(new FileOutputStream(file), DEFAULT_BUFFER_SIZE);
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            for (int length = 0; ((length = input.read(buffer)) > 0); ) {
                output.write(buffer, 0, length);
            }
        } finally {
            if (output != null)
                try {
                    output.close();
                } catch (IOException logOrIgnore) {
                }
            if (input != null)
                try {
                    input.close();
                } catch (IOException logOrIgnore) {
                }
        }
        part.delete();
    }
}