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
public class RetrieveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f0f8ff; color: #333; }");
        out.println(".container { background: white; padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); width: 80%; max-width: 600px; margin: auto; }");
        out.println(".user-info { border-bottom: 1px solid #ddd; padding: 10px 0; }");
        out.println(".user-info:last-child { border-bottom: none; }");
        out.println(".user-info p { margin: 5px 0; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>");
        out.println("<h1>User Information</h1>");
        
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/userInfo", "postgres", "123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, email FROM users");
            
            while (rs.next()) {
                out.println("<div class='user-info'>");
                out.println("<p><strong>Name:</strong> " + rs.getString("name") + "</p>");
                out.println("<p><strong>Email:</strong> " + rs.getString("email") + "</p>");
                out.println("</div>");
            }
            
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        out.println("</div>");
        out.println("</body></html>");
        out.close();
    }
}
