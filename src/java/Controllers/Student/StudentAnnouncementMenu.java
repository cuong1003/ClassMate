package Controllers.Student;

import DAL.AssignmentDAO;
import DAL.UserDAO;
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
        UserDAO udao = new UserDAO();
        String ccode = request.getParameter("ccode");
        request.setAttribute("ccode", ccode);
        List<Assignment> announcementList = AssignmentDAO.getAnnouncementsList(ccode);
        if (announcementList != null) {    
            int createdById = announcementList.get(0).getCreatedBy();
            Users us = udao.GetUserById(createdById);
            String namecreatedby = us.getFullName();
            request.setAttribute("teacher", namecreatedby);
        }
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
