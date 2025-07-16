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

/**
 *
 * @author dinhh
 */

public class UserUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rawId = request.getParameter("id");
        int userId = Integer.parseInt(rawId);

        UserDAO dao = new UserDAO();
        Users us = dao.GetUserById(userId);

        if (us == null) {
            request.setAttribute("error", "Không tìm thấy người dùng.");
            request.getRequestDispatcher("/Views/Error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("user", us);
        request.getRequestDispatcher("/Views/Shared/UpdateUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");

        UserDAO dao = new UserDAO();
        dao.updateUserInfo(id, fullname, email);

        response.sendRedirect(request.getContextPath() + "/UserInfoController?id=" + id);
    }
}
