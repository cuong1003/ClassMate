/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers.Student;

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
 * @author dinhh
 */
public class ShowMemberController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        List<Integer> userIds = new ArrayList<>();

        for (ClassroomMember cm : members) {
            names.add(dao.getFullnameByUserId(cm.getUser_id()));
            emails.add(dao.getEmailByUserId(cm.getUser_id()));
            userIds.add(cm.getUser_id());
        }
        // gui sang jsp
        request.setAttribute("members", members);
        request.setAttribute("names", names);
        request.setAttribute("emails", emails);
        request.setAttribute("userIds", userIds);
        request.setAttribute("teacherName", teacherName);
        request.setAttribute("ccode", classCode);
        request.setAttribute("membersCount", members.size());
        request.getRequestDispatcher("/Views/Student/showClassMembers.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}