
package org.example.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
@WebServlet("/students")

public class displayrecords extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            //load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //make Connection
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","manikanta@07");
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from students");
            resp.setContentType("text/html");
            PrintWriter out=resp.getWriter();
            out.println("<html>");
            out.println("<body>");
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>NAME</th>");
            out.println("<th>AGE</th>");

            out.println("</tr>");
            while(rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id")+"</td>");
                out.println("<td>" + rs.getString("name")+"</td>");
                out.println("<td>" + rs.getInt("age")+"</td>");

                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}