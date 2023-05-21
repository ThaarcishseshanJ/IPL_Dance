import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class update extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();
        // Get the customer name as input from the user
        String Name = request.getParameter("Name");
        String Age = request.getParameter("Age");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dancedb", "root", "");
            if (conn != null) {
                out.println("<h1> Connection established successfully </h1>");
            }
            stmt = conn.createStatement();

            // Update the contact information of the customer with the given name
            String sql = "UPDATE dancer SET Age='" + Age + "' WHERE Name='" + Name + "'";
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                out.println("<h2> Dancer age updated successfully </h2>");
            } else {
                out.println("<h2> Error updating age </h2>");
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Error connecting to DB - Error:" + e);
        }
    }
}