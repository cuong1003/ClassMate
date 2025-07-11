/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Teacher;

import DAL.AssignmentDAO;
import Models.Assignment;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherShowAssignment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ccode = request.getParameter("ccode");
            AssignmentDAO assDao = new AssignmentDAO();
            List<Assignment> ass = assDao.getAssignByCcode(ccode);
            request.setAttribute("assignments", ass);
            request.getRequestDispatcher("/Views/Teacher/showClassAssignments.jsp").forward(request, response);
            //processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TeacherShowAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
