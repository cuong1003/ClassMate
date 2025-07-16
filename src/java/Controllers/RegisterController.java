package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import DAL.UserDAO;
import jakarta.servlet.http.HttpSession;

/**
 * RegisterController for ClassMate system
 */
public class RegisterController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/LandingPage/Register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        int role = Integer.parseInt(request.getParameter("role"));
        // Basic input validation
    if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty() ||
        fullname == null || fullname.trim().isEmpty() || email == null || email.trim().isEmpty()  )
          {
        request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin.");
        request.getRequestDispatcher("Views/LandingPage/Register.jsp").forward(request, response);
        return;
    }

    
    
   
        // Check if username already exists
    if (dao.isUsernameExists(username)) {
        // Username exists, set error message and forward back to registration page
        request.setAttribute("errorMessage", "Tên tài khoản đã tồn tại. Vui lòng tạo tài khoản khác khác.");
        request.getRequestDispatcher("Views/LandingPage/Register.jsp").forward(request, response);
    } else {
        // Username does not exist, proceed with registration
        dao.register(username, password, fullname, email, role);
        HttpSession ses = request.getSession();
        ses.setAttribute("u", username);
        ses.setAttribute("r", role);
        response.sendRedirect(request.getContextPath() + "/login");
    }
} 
}