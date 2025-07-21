/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Teacher;

import DAL.SubmissionDAO;
import Models.Submission;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;


public class TeacherViewSubmission extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String ccode = request.getParameter("ccode");
        List<Submission> slist = SubmissionDAO.getAllSubmissionByAssignmentId(id);
        request.setAttribute("ccode", ccode);
        request.setAttribute("slist", slist);
        request.getRequestDispatcher("/Views/Teacher/viewSubmission.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/t/danhsachbaitap?ccode=");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
