package Controllers.Teacher;

import DAL.AssignmentDAO;
import DAL.ClassroomDAO;
import DAL.UserDAO;
import Models.Assignment;
import Models.Classroom;
import Models.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * TeacherAnnouncementMenu for ClassMate system
 */
public class TeacherAnnouncementMenu extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Handle teacher class management
        //Hiển thị thông tin Bản tin của lớp,...
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
        request.getRequestDispatcher("/Views/Teacher/classManagement.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        String ccode = request.getParameter("ccode");
        Users us = (Users) ses.getAttribute("us");
        String title = request.getParameter("title").trim();
        String des = request.getParameter("description").trim();
        boolean created = AssignmentDAO.createAnnouncement(AssignmentDAO.getClassroomId(ccode),title,des,us.getUserId());
        if (created) {
            ses.setAttribute("alert", "Tạo thông báo thành công!");
        } else {
            ses.setAttribute("alert", "Lỗi không tạo được thông báo");
        }
        response.sendRedirect(request.getContextPath() + "/t/bangtin?ccode="+ccode);
        
    }
} 