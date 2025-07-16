package Controllers.Student;

import DAL.ClassroomDAO;
import Models.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * StudentClassController for ClassMate system
 */
public class StudentClassController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Handle student class operations
        String ccode = request.getParameter("ccode");
        request.setAttribute("ccode", ccode);
        request.getRequestDispatcher("/Views/Student/classList.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String classCode = request.getParameter("ccode");
        HttpSession session = request.getSession();
        Users us = (Users) session.getAttribute("us");

        if (classCode != null && us != null) {
            ClassroomDAO dao = new ClassroomDAO();
            dao.delete(us.getUserId(), classCode);
        }

        response.sendRedirect(request.getContextPath() + "/s/studenthome");
    }
} 