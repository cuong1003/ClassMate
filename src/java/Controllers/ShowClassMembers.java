/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

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
 * @author c9
 */
public class ShowClassMembers extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowClassMembers</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowClassMembers at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

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

// Lấy giáo viên
String teacherName = dao.getTeacherFullnameByClassCode(classCode);

// Lấy tên + email học sinh như trước...
List<String> names = new ArrayList<>();
List<String> emails = new ArrayList<>();

for (ClassroomMember cm : members) {
    names.add(dao.getFullnameByUserId(cm.getUser_id()));
    emails.add(dao.getEmailByUserId(cm.getUser_id()));
}

request.setAttribute("members", members);
request.setAttribute("names", names);
request.setAttribute("emails", emails);
request.setAttribute("teacherName", teacherName);  // gửi sang JSP
request.setAttribute("ccode", classCode);
request.setAttribute("membersCount", members.size());
request.getRequestDispatcher("/Views/Shared/showClassMembers.jsp").forward(request, response);

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
        processRequest(request, response);
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
