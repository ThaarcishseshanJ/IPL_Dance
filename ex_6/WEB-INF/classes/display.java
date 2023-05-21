import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class display extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dancedb", "root", "");
            if (conn != null) {
                out.println("<h1> Connection established successfully </h1>");
            }
            stmt = conn.createStatement();

            // Update the contact information of the customer with the given name
            String sql = "Select * from dancer";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Retrieve by column name
                String Name = rs.getString("Name");
                String Dob = rs.getString("Dob");
                String Style = rs.getString("Style");
                String Age = rs.getString("Age");
                // Display values
                out.println("<p> Name: " + Name + "<br>");
                out.println("Dob: " + Dob + "<br>");
                out.println("Style: " + Style + "<br>");
                out.println("Age: " + Age + " %<br></p>");
            }
            out.println("</body></html>");
            rs.close();
            // Clean-up environment
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.print("Error connecting to DB - Error:" + e);
        }
    }
}