import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateSer extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userInfo", "postgres", "123");
            String query = "UPDATE users SET name=?, email=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, Integer.parseInt(id));
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
            	response.sendRedirect("retrieve.jsp");
            } else {
                out.println("<p>Failed to update record!</p>");
            }
            
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        out.close();
    }
}
