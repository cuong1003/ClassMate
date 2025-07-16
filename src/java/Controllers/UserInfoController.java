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
public class UserInfoController extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    String rawId = request.getParameter("id");
    int userId = -1;
    
    // Kiểm tra đầu vào có hợp lệ không
    if (rawId != null && !rawId.trim().isEmpty()) {
        try {
            userId = Integer.parseInt(rawId.trim());
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID không hợp lệ.");
            request.getRequestDispatcher("/Views/Error.jsp").forward(request, response);
            return;
        }
    } else {
        request.setAttribute("error", "Thiếu tham số ID.");
        request.getRequestDispatcher("/Views/Error.jsp").forward(request, response);
        return;
    }

    // Lấy user từ DAO
    UserDAO usdao = new UserDAO();
    Users us = usdao.GetUserById(userId);

    // Kiểm tra nếu không có user (ví dụ: ID không tồn tại)
    if (us == null) {
        request.setAttribute("error", "Không tìm thấy người dùng.");
        request.getRequestDispatcher("/Views/Error.jsp").forward(request, response);
        return;
    }

    // Truyền dữ liệu sang JSP
    request.setAttribute("user", us);
    request.getRequestDispatcher("/Views/Shared/UserInfo.jsp").forward(request, response);
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
