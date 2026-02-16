//Better to use eclipse  for this project as it is a web application
// and requires servlet support.
// This servlet generates an email and password based on user input and displays it on a web page.
//tomcat server is required to run this servlet. You can deploy it on a local tomcat server and access it via a web browser.
package com.emailapp;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        // Get data
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String dept = request.getParameter("dept");

        // Generate email
        String email = first.toLowerCase() + "." +
                       last.toLowerCase() + "@" +
                       dept + ".company.com";

        // Generate password
        String password = generatePassword(8);

        int mailbox = 500;

        // Send response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Generated Details</h2>");

        out.println("Name: " + first + " " + last + "<br>");
        out.println("Department: " + dept + "<br>");
        out.println("Email: " + email + "<br>");
        out.println("Password: " + password + "<br>");
        out.println("Mailbox: " + mailbox + "MB<br>");

        out.println("</body></html>");
    }

    // Password Generator
    private String generatePassword(int length) {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                     + "abcdefghijklmnopqrstuvwxyz"
                     + "0123456789@#$";

        Random r = new Random();
        String pass = "";

        for(int i=0;i<length;i++){
            int index = r.nextInt(chars.length());
            pass += chars.charAt(index);
        }

        return pass;
    }
}
