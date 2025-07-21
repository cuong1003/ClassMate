/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Student;

import DAL.ClassroomDAO;
import Models.Classroom;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fakey
 */
public class StudentHome extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassroomDAO classdao = new ClassroomDAO();
        HttpSession ses = request.getSession();
        Users us = (Users)ses.getAttribute("us");
        List<Classroom> danhsachlop = classdao.getClassByUserId(us.getUserId());
        request.setAttribute("danhsachlop", danhsachlop);
        
        request.getRequestDispatcher("/Views/Student/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    ClassroomDAO classdao = new ClassroomDAO();
    HttpSession ses = request.getSession();
    Users us = (Users) ses.getAttribute("us");
    String ccode = request.getParameter("ccode");

    

    if (request.getParameter("leave") != null && ccode != null && !ccode.trim().isEmpty()) {
        classdao.deleteStudent(us.getUserId(), ccode);
        response.sendRedirect(request.getContextPath() + "/s/studenthome");
        return; // Ngăn không cho thực thi tiếp
    }

    if (ccode != null && !ccode.trim().isEmpty()) {
        try {
            classdao.joinClassroom(us.getUserId(), ccode);
        } catch (Exception ex) {
            Logger.getLogger(StudentHome.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Có lỗi khi tham gia lớp học: " + ex.getMessage());
            request.getRequestDispatcher("/Views/Student/studenthome.jsp").forward(request, response);
            return; // Ngăn không cho thực thi tiếp
        }
    }

    // Chỉ chuyển hướng nếu không có lỗi và không có hành động nào khác
    response.sendRedirect(request.getContextPath() + "/s/studenthome");
}

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
