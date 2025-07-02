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
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassroomDAO classdao = new ClassroomDAO();
        HttpSession ses = request.getSession();
        Users us = (Users)ses.getAttribute("us");
        String classname = request.getParameter("className").trim();
        String classcode = request.getParameter("classCode").trim();
        classdao.createClass(classname,classcode,us.getRole());
        request.getRequestDispatcher("Views/LandingPage/Login.jsp").forward(request, response);
        
    }
} 