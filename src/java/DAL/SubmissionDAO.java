package DAL;

import Models.Submission;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubmissionDAO {
    // Kết nối cơ sở dữ liệu

    // Tạo bài nộp mới
    public static boolean createSubmission(Submission submission) throws Exception {
        String sql = "INSERT INTO Submission (assignment_id, user_id, submit_text, file_url, submitted_at, grade, feedback) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, submission.getAssignment_id());
            ps.setInt(2, submission.getUser_id());
            ps.setString(3, submission.getSubmit_text());
            ps.setString(4, submission.getFileUrl());
            ps.setDate(5, new java.sql.Date(submission.getSubmmitted_at().getTime()));
            ps.setInt(6, submission.getGrade());
            ps.setString(7, submission.getFeedback());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        submission.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy bài nộp theo assignment_id và user_id
    public static Submission getSubmissionByAssignmentAndUser(int assignmentId, int userId) {
        Submission submission = null;
        String sql = "SELECT * FROM Submission WHERE assignment_id = ? AND user_id = ?";

        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, assignmentId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                submission = new Submission();
                submission.setId(rs.getInt("id"));
                submission.setAssignment_id(rs.getInt("assignment_id"));
                submission.setUser_id(rs.getInt("user_id"));
                submission.setSubmit_text(rs.getString("submit_text"));
                submission.setFileUrl(rs.getString("file_url"));
                submission.setSubmmitted_at(rs.getDate("submitted_at"));
                submission.setGrade(rs.getInt("grade"));
                submission.setFeedback(rs.getString("feedback"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(SubmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return submission;
    }

    // Cập nhật bài nộp 
    public boolean updateSubmission(Submission submission) {
        String sql = "UPDATE Submission SET submit_text = ?, file_url = ?,submitted_at = ?, grade = ?, feedback = ? WHERE id = ?";

        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, submission.getSubmit_text());
            ps.setString(2, submission.getFileUrl());
            ps.setDate(3, new java.sql.Date(submission.getSubmmitted_at().getTime()));
            ps.setInt(4, submission.getGrade());
            ps.setString(5, submission.getFeedback());
            ps.setInt(6, submission.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(SubmissionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Lấy danh sách bài tập qua id bài tập (dùng cho bên giáo viên)
    public static List<Submission> getAllSubmissionByAssignmentId(int assignment_id) {
        String sql = "SELECT a.id, a.assignment_id, a.[user_id], \n"
                + "b.username, a.submit_text, a.file_url, \n"
                + "a.submitted_at, a.grade, a.feedback \n"
                + "FROM Submission a LEFT JOIN [User] b \n"
                + "ON a.[user_id] =  b.id\n"
                + "WHERE a.assignment_id = ?";
        List<Submission> slist = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, assignment_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int assId = rs.getInt("assignment_id");
                int user_id = rs.getInt("user_id");
                String userName = rs.getString("username");
                String title = rs.getString("submit_text");
                String url = rs.getString("file_url");
                Date submitted_at = rs.getDate("submitted_at");
                int grade = rs.getInt("grade");
                String fb = rs.getString("feedback");
                slist.add(new Submission(id, assId, user_id, userName, title, url, submitted_at, grade, fb));

            }
            return slist;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        List<Submission> list = SubmissionDAO.getAllSubmissionByAssignmentId(1);
        for (Submission a : list) {
            System.out.println(a);
        }
    }
}
