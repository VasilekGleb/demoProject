<%--
  Created by IntelliJ IDEA.
  User: b_ege
  Date: 06.08.2022
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Uploader</title>
</head>
<body>
    <form action="/api/v1/file/upload" method="post" name="fileUploadForm" enctype="multipart/form-data">
        <input name="file" type="file">
        <button type="submit">Upload file!</button>
    </form>
</body>
</html>
