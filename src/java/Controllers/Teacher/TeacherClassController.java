package Controllers.Teacher;

import DAL.ClassroomDAO;
import Models.Classroom;
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
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassroomDAO classdao = new ClassroomDAO();
        Classroom c = new Classroom();
        HttpSession ses = request.getSession();
        int userId = (int) ses.getAttribute("userId");
        String classname = request.getParameter("className").trim();
        String classcode = request.getParameter("classCode").trim();
        classdao.createClass(classname,classcode,userId);
        request.getRequestDispatcher("Views/LandingPage/Login.jsp").forward(request, response);
        
    }
} 