package Controllers.Teacher;

import DAL.ClassroomDAO;
import Models.Classroom;
import Models.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


/**
 * TeacherClassController for ClassMate system
 */
public class TeacherClassController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Handle teacher class management
        //Hiển thị thông tin Bản tin của lớp,...
        String ccode = request.getParameter("ccode");
        request.setAttribute("ccode", ccode);
        request.getRequestDispatcher("/Views/Teacher/classManagement.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
} 