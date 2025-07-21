/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Student;

import DAL.AssignmentDAO;
import Models.Assignment;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author fakey
 */
public class StudentSubmitAssignment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int assigId = Integer.parseInt(request.getParameter("id"));
            String ccode = request.getParameter("ccode");
            Assignment baitap = AssignmentDAO.getAssigmentById(assigId);
            request.setAttribute("assignment", baitap);
            request.setAttribute("ccode", ccode);
            request.getRequestDispatcher("/Views/Student/SubmitAssignmentSite.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e+"submitsubmitassignment");
        }        
        //cho ló chạy lại nếu nó sai lầm
        response.sendRedirect(request.getContextPath() + "/t/nopbaitap?id="+ request.getParameter("id").trim()
                +"&"+request.getParameter("ccode").trim());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
