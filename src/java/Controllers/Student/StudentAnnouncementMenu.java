package Controllers.Student;

import DAL.AssignmentDAO;
import Models.Assignment;
import Models.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * StudentAnnouncementMenu for ClassMate system
 */
public class StudentAnnouncementMenu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ccode = request.getParameter("ccode");
        List<Assignment> announcementList = AssignmentDAO.getAnnouncementsList(ccode);
        request.setAttribute("announcementList", announcementList);
        request.getRequestDispatcher("/Views/Student/classManagement.jsp").forward(request, response);
        // TODO: Handle student class operations
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Handle student class POST operations
    }
}
