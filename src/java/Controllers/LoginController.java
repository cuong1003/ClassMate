/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.UserDAO;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author c9
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession ses = request.getSession();
        
        // check tồn tại sessionsession
        String tempUsername = (String) ses.getAttribute("u");
        Integer tempRole = (Integer) ses.getAttribute("r");  // dùng Integer để tránh null (int sẽ lỗi khi null)
        
        // Chỉ auto-fill khi có username và role từ register
        if (tempUsername != null && tempRole != null) {
            // set atriibute cho jsp de auto-fill
            request.setAttribute("autoFillUsername", tempUsername);
            request.setAttribute("autoFillRole", tempRole.toString());  // Convert to String for JSP
            
            // Kill session khi set atrribute xong
            ses.removeAttribute("u");
            ses.removeAttribute("r");
        }
        
        request.getRequestDispatcher("Views/LandingPage/Login.jsp").forward(request, response);
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        processRequest(request, response);
          UserDAO dao = new UserDAO();
          String u = request.getParameter("username").trim();
          String p = request.getParameter("password").trim();
          String r = request.getParameter("role");
          //debug xem co u p ko
          request.setAttribute("uu", u);
          
          Users loginUser = dao.checkLogin(u, p, r);
          if (loginUser != null) {
              HttpSession ses = request.getSession();
              ses.setAttribute("us",loginUser);
              
              if (loginUser.getRoll() == 0) {  // Giáo viên
                  response.sendRedirect(request.getContextPath() + "/Teacher/TeacherHome");
              } else if (loginUser.getRoll() == 1) {  // Học sinh
                  response.sendRedirect(request.getContextPath() + "/Student/StudentHome");
              } else {
                  // chuyển lại về url mặc định (LandingPage)
                  response.sendRedirect(request.getContextPath() + "/");
              }
          } else {
              request.setAttribute("fail", "User or Password wrong!");
              request.getRequestDispatcher("Views/LandingPage/Login.jsp").forward(request, response);
          }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>//asd

}
