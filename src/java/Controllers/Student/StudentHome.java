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
        String studentName = us.getFullname();
        int studentId = us.getUserId();
        request.setAttribute("danhsachlop", danhsachlop);
        request.setAttribute("studentName", studentName);
        request.setAttribute("studentId", studentId);
        request.getRequestDispatcher("/Views/Student/home.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassroomDAO classdao = new ClassroomDAO();
        HttpSession ses = request.getSession();
        Users us = (Users)ses.getAttribute("us");
        String classcode = request.getParameter("classCode").trim();
        try {
            classdao.joinClassroom(us.getUserId(), classcode);
        } catch (Exception ex) {
            Logger.getLogger(StudentHome.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(request.getContextPath() + "/s/studenthome");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
