import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/retrieve")
public class RetrieveSer extends HttpServlet{

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f0f8ff; color: #333; }");
        out.println(".container { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); width: 80%; max-width: 800px; margin: auto; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        out.println("th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }");
        out.println("th { background-color: #4682b4; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>");
        out.println("<h1>User Information</h1>");
        
        out.println("<table>");
        out.println("<tr><th>Name</th><th>Email</th><th>Action</th></tr>");
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userInfo", "postgres", "123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, email FROM users");
            
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("<td>");
                out.println("<a href='update.jsp?id=" + id + "'>Update</a> | ");
                out.println("<a href='delete.jsp?id=" + id + "'>Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        out.println("</table>");
        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }
}