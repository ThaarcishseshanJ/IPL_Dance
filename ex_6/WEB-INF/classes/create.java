import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class create extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stmt = null;
        PrintWriter out = response.getWriter();

        String Name = request.getParameter("Name");
        String Dob = request.getParameter("Dob");
        String Style = request.getParameter("Style");
        String Age = request.getParameter("Age");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dancedb", "root", "");
            stmt = conn.createStatement();

            String sql = "INSERT INTO dancer(Name,Dob,Style,Age) values(?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.setString(2, Dob);
            pstmt.setString(3, Style);
            pstmt.setString(4, Age);
            int rowsAffected = pstmt.executeUpdate();

            // Display the number of rows affected by the DELETE statement
            out.println("<html><body>");
            out.println("<h3>Rows affected: " + rowsAffected + "</h3>");
            out.println("</body></html>");

            // Clean up resources
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}