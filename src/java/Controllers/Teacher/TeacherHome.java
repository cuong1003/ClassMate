
package Controllers.Teacher;

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

public class TeacherHome extends HttpServlet {
    //DoGet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ClassroomDAO classdao = new ClassroomDAO();
        HttpSession ses = request.getSession();
        Users us = (Users)ses.getAttribute("us");
        List<Classroom> danhsachlop = classdao.getClassByOwnerId(us.getUserId());
        request.setAttribute("danhsachlop", danhsachlop);
        request.getRequestDispatcher("/Views/Teacher/home.jsp").forward(request, response);
    }
    //DoPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassroomDAO classdao = new ClassroomDAO();
        HttpSession ses = request.getSession();
        Users us = (Users)ses.getAttribute("us");
        String classname = request.getParameter("className").trim();
        String classcode = request.getParameter("classCode").trim();
        classdao.createClass(classname,classcode,us.getUserId());
        request.getRequestDispatcher("/Teacher/TClassDashBoard").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
