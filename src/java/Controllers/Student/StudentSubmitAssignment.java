/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Student;

import DAL.AssignmentDAO;
import DAL.SubmissionDAO;
import Models.Assignment;
import Models.Submission;
import Models.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * @author fakey
 */
public class StudentSubmitAssignment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int assigId = Integer.parseInt(request.getParameter("id"));
            String ccode = request.getParameter("ccode");
            Assignment baitap = AssignmentDAO.getAssigmentById(assigId);
            HttpSession session = request.getSession();
            Users us =  (Users)session.getAttribute("us");
            int userId = us.getUserId();
            Submission hehe = SubmissionDAO.getSubmissionByAssignmentAndUser(assigId, userId);
            request.setAttribute("submission", hehe);
            request.setAttribute("assignment", baitap);
            request.setAttribute("ccode", ccode);
            request.getRequestDispatcher("/Views/Student/SubmitAssignmentSite.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println(e+"submitsubmitassignment");
        }        
        //cho ló chạy lại nếu nó sai lầm
        response.sendRedirect(request.getContextPath() + "/t/nopbaitap?id="+ request.getParameter("id").trim()
                +"&"+request.getParameter("ccode").trim());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    try {
        // Đặt mã hóa ký tự để xử lý tiếng Việt (UTF-8)
        request.setCharacterEncoding("UTF-8");

        // Lấy tham số từ biểu mẫu
        int assignmentId = Integer.parseInt(request.getParameter("assignment_id"));
        String submitText = request.getParameter("submit_text");
        String fileUrl = request.getParameter("file_url");

        // Kiểm tra trường bắt buộc
        if (submitText == null || submitText.trim().isEmpty()) {
            request.setAttribute("error", "Nội dung bài làm không được để trống.");
            request.setAttribute("assignment", AssignmentDAO.getAssigmentById(assignmentId));
            request.getRequestDispatcher("/Views/Student/SubmitAssignmentSite.jsp").forward(request, response);
            return;
        }

        // Lấy ID người dùng từ session
        HttpSession session = request.getSession();
//        Integer userId = (Integer) session.getAttribute("user_id");
//        if (userId == null) {
//            System.out.println("Session user_id is null, redirecting to login.");
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
Users us =  (Users)session.getAttribute("us");
        int id = us.getUserId();
        // Tạo đối tượng Submission
        Submission submission = new Submission();
        submission.setAssignment_id(assignmentId);
        submission.setUser_id(id);
        submission.setSubmit_text(submitText);
        submission.setFileUrl(fileUrl != null && !fileUrl.trim().isEmpty() ? fileUrl : null); // Xử lý fileUrl tùy chọn
        submission.setSubmmitted_at(new Date()); // Đặt thời gian hiện tại
        submission.setGrade(-1); // Giá trị mặc định theo ràng buộc SQL
        submission.setFeedback(null); // Feedback ban đầu là null

        // Kiểm tra xem người dùng đã nộp bài cho bài tập này chưa
        Submission existingSubmission = SubmissionDAO.getSubmissionByAssignmentAndUser(assignmentId, id);
        if (existingSubmission != null) {
            request.setAttribute("error", "Bạn đã nộp bài tập này rồi. Vui lòng chỉnh sửa bài nộp hiện có nếu cần.");
            request.setAttribute("assignment", AssignmentDAO.getAssigmentById(assignmentId));
            request.getRequestDispatcher("/Views/Student/SubmitAssignmentSite.jsp").forward(request, response);
            return;
        }

        // Lưu bài nộp vào cơ sở dữ liệu
        boolean success = SubmissionDAO.createSubmission(submission);
        if (success) {
            // Chuyển hướng về danh sách bài tập với thông báo thành công
            request.getSession().setAttribute("success", "Nộp bài tập thành công!");
            response.sendRedirect(request.getContextPath() + "/s/danhsachbaitap?ccode=" + request.getParameter("ccode"));
        } else {
            // Xử lý lỗi khi thêm vào cơ sở dữ liệu
            request.setAttribute("error", "Đã có lỗi xảy ra khi nộp bài. Vui lòng thử lại.");
            request.setAttribute("assignment", AssignmentDAO.getAssigmentById(assignmentId));
            request.getRequestDispatcher("/Views/Student/SubmitAssignmentSite.jsp").forward(request, response);
        }

    } catch (NumberFormatException e) {
        // Xử lý lỗi khi assignment_id không hợp lệ
        request.setAttribute("error", "ID bài tập không hợp lệ.");
        request.getRequestDispatcher("/Views/Student/SubmitAssignmentSite.jsp").forward(request, response);
    } catch (Exception e) {
        // Xử lý các lỗi bất ngờ khác
        e.printStackTrace();
        request.setAttribute("error", "Đã có lỗi xảy ra. Vui lòng thử lại sau.");
        request.getRequestDispatcher("/Views/Student/SubmitAssignmentSite.jsp").forward(request, response);
    }
}
    

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
