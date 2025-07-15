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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; // <-- Đây là java.util.Date

public class TeacherShowAssignment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ccode = request.getParameter("ccode");
            AssignmentDAO assDao = new AssignmentDAO();
            List<Assignment> ass = assDao.getAssignByCcode(ccode);
            request.setAttribute("assignments", ass);
            request.setAttribute("ccode", ccode); // Thêm dòng này để truyền ccode sang JSP
            request.getRequestDispatcher("/Views/Teacher/showClassAssignments.jsp").forward(request, response);
            //processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(TeacherShowAssignment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AssignmentDAO assDao = new AssignmentDAO();
        String ccode = request.getParameter("ccode");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String fileUrl = request.getParameter("fileUrl"); // Google Drive URL
        
        // Debug logging
        System.out.println("=== DEBUG CREATE ASSIGNMENT ===");
        System.out.println("ccode: " + ccode);
        System.out.println("title: " + title);
        System.out.println("description: " + description);
        System.out.println("createdBy param: " + request.getParameter("createdBy"));
        
        int createdBy = Integer.parseInt(request.getParameter("createdBy"));

        String deadlineStr = request.getParameter("deadline");
        java.util.Date utilDeadline = null; // Khai báo là java.util.Date

        if (deadlineStr != null && !deadlineStr.isEmpty()) {
            try {
                // HTML input type="date" trả về format yyyy-MM-dd
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                utilDeadline = formatter.parse(deadlineStr); // Nhận về java.util.Date
            } catch (ParseException e) {
                System.err.println("Lỗi phân tích cú pháp ngày deadline: " + e.getMessage());
                response.getWriter().println("Lỗi: Ngày deadline không hợp lệ. Vui lòng nhập đúng định dạng yyyy-MM-dd.");
                return; // Dừng xử lý nếu có lỗi
            }
        }

        java.sql.Timestamp sqlDeadline = null; // Khai báo là java.sql.Timestamp

        if (utilDeadline != null) {
            // Chuyển đổi từ java.util.Date sang java.sql.Timestamp để tương thích DATETIME
            sqlDeadline = new java.sql.Timestamp(utilDeadline.getTime());
        }

// Kiểm tra xem sqlDeadline có giá trị để tránh NullPointerException khi gọi DAO
        if (sqlDeadline != null) {
            try {
                System.out.println("createdBy: " + createdBy);
                System.out.println("sqlDeadline: " + sqlDeadline);
                System.out.println("Attempting to insert assignment...");
                
                assDao.addAssignmentWithFile(ccode, title, description, createdBy, sqlDeadline, fileUrl);
                
                System.out.println("Assignment inserted successfully!");
                response.sendRedirect(request.getContextPath() + "/t/assignmentlist?ccode=" + ccode);
            } catch (Exception ex) {
                System.err.println("Error inserting assignment: " + ex.getMessage());
                Logger.getLogger(TeacherShowAssignment.class.getName()).log(Level.SEVERE, null, ex);
                response.getWriter().println("Lỗi khi tạo bài tập: " + ex.getMessage());
            }
        } else {
            response.getWriter().println("Lỗi: Không thể xử lý ngày deadline. Vui lòng thử lại.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
