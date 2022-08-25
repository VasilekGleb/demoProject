package com.example.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {

    private static String string;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        PrintWriter pw = response.getWriter();

        if (name.equals("allUsers")) {
            pw.println("<html>");
            pw.println("<h1> All users: " + getString() + "</h1>");
            pw.println("</html>");
        } else if (getString() == null) {
            setString(name);
        } else {
            setString(getString() + ", " + name);
        }

        pw.println("<html>");
        pw.println("<h1> Hello, " + name + "</h1>");
        pw.println("</html>");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static String getString() {
        return string;
    }

    public static void setString(String string) {
        Servlet.string = string;
    }
}
