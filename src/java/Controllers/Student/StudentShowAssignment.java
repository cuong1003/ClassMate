
package Controllers.Student;

import Controllers.Teacher.TeacherShowAssignment;
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

public class StudentShowAssignment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ccode = request.getParameter("ccode");
            List<Assignment> ass = AssignmentDAO.getAssignmentsList(ccode);
            request.setAttribute("assignments", ass);
            request.setAttribute("ccode", ccode); // Thêm dòng này để truyền ccode sang JSP
            request.getRequestDispatcher("/Views/Student/showClassAssignments.jsp").forward(request, response);
            //processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TeacherShowAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }
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
