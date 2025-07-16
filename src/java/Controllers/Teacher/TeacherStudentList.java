/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Teacher;

import DAL.ClassroomDAO;
import Models.ClassroomMember;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fakey
 */
public class TeacherStudentList extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classCode = request.getParameter("ccode");

        ClassroomDAO dao = new ClassroomDAO();
        List<ClassroomMember> members = dao.loadClassMembers(classCode);

        // lay ten giao vien
        String teacherName = dao.getTeacherFullnameByClassCode(classCode);

        // lay ten voi email hs
        List<String> names = new ArrayList<>();
        List<String> emails = new ArrayList<>();

        for (ClassroomMember cm : members) {
            names.add(dao.getFullnameByUserId(cm.getUser_id()));
            emails.add(dao.getEmailByUserId(cm.getUser_id()));
        }
        // gui sang jsp
        request.setAttribute("members", members);
        request.setAttribute("names", names);
        request.setAttribute("emails", emails);
        request.setAttribute("teacherName", teacherName);
        request.setAttribute("ccode", classCode);
        request.setAttribute("membersCount", members.size());
        request.getRequestDispatcher("/Views/Teacher/showClassMembers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String classCode = request.getParameter("ccode");
        String userIdStr = request.getParameter("userId");
        ClassroomDAO dao = new ClassroomDAO();
        try {
            int userId = Integer.parseInt(userIdStr);
            dao.delete(userId, classCode); // Gọi phương thức xóa
            // Chuyển hướng về trang danh sách thành viên lớp
            response.sendRedirect(request.getContextPath() + "/t/studentlist?ccode=" + classCode);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID người dùng không hợp lệ.");
            doGet(request, response); // Tải lại trang với thông báo lỗi
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
