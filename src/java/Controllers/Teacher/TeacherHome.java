
package Controllers.Teacher;

import Controllers.Utils.FileManager;
import DAL.ClassroomDAO;
import Models.Classroom;
import Models.Users;
import jakarta.servlet.ServletContext;
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
        request.setAttribute("danhsachlop", danhsachlop);
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
        FileManager file = new FileManager();
        ServletContext context = getServletContext();     //Lấy context của project từ sẻvlet
        boolean success = file.createClassroomFolders(context, classid);
        if (success) {
            ses.setAttribute("folderalert", "Tạo folder lớp học thành công");
        } else {
            ses.setAttribute("folderalert", "Lỗi tạo folder lớp học");
        }
        System.out.println(ses.getAttribute("folderalert")+"");
        // chuyển hướng lại trang home nhằm method get load lại danh sách lớp.
        response.sendRedirect(request.getContextPath() + "/t/teacherhome");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
