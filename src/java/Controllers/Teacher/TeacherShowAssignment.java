/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Teacher;

import DAL.AssignmentDAO;
import Models.Assignment;
import Models.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherShowAssignment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ccode = request.getParameter("ccode");
            List<Assignment> ass = AssignmentDAO.getAssignmentsList(ccode);
            request.setAttribute("assignments", ass);
            request.setAttribute("ccode", ccode); // Thêm dòng này để truyền ccode sang JSP
            request.getRequestDispatcher("/Views/Teacher/showClassAssignments.jsp").forward(request, response);
            //processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TeacherShowAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users)session.getAttribute("us");
        int createdBy = user.getUserId(); // Lấy Id của người tạo.
        String ccode = request.getParameter("ccode"); // Lấy ccode từ form.
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String file_url = request.getParameter("file_url");
        String deadlineStr = request.getParameter("deadline"); //"2024-01-15T14:30"
        java.sql.Timestamp deadline = java.sql.Timestamp.valueOf(deadlineStr.replace("T", " ") + ":00"); // -> "2024-01-15 14:30:00"
        int classroomId = AssignmentDAO.getClassroomId(ccode); //Lấy Id của classroom từ classcode.
        AssignmentDAO.createAssignment(classroomId, title, description, createdBy, deadline, file_url);
        response.sendRedirect(request.getContextPath() + "/t/assignmentlist?ccode=" + ccode);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
