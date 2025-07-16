
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
        // Lấy danh sách lớp của Giáo viên để hiển thị.
        ClassroomDAO classdao = new ClassroomDAO();
        HttpSession ses = request.getSession();
        Users us = (Users)ses.getAttribute("us");
        List<Classroom> danhsachlop = classdao.getClassByOwnerId(us.getUserId());
        String teacherName = us.getFullname();
        int teacherId = us.getUserId();
        request.setAttribute("danhsachlop", danhsachlop);
        request.setAttribute("teacherName", teacherName);
        request.setAttribute("teacherId", teacherId);
        request.getRequestDispatcher("/Views/Teacher/home.jsp").forward(request, response);
    }
    //DoPost
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Tạo lớp học 
        ClassroomDAO classdao = new ClassroomDAO();
        HttpSession ses = request.getSession();
        Users us = (Users)ses.getAttribute("us");
        String classname = request.getParameter("className").trim();
        String classcode = request.getParameter("classCode").trim();
        classdao.createClass(classname,classcode,us.getUserId());
        int classid = classdao.getClassroomIdByCode(classcode); //lấy Id của lớp vừa tạo để tạo Folder lưu dữ liệu
        //Tạo folder lưu dữ liệu của lớp
        //...ToBeContinue....
        response.sendRedirect(request.getContextPath() + "/t/teacherhome");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
