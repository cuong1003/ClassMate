package DAL;

import Models.Assignment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * AssignmentDAO for ClassMate system
 */
public class AssignmentDAO {
    // TODO: Add CRUD methods for Assignment
    
    // Tạo bài tập (Assignment)
    public static boolean createAssignment(int classroomId, String title, String description, int createdBy, java.sql.Timestamp deadline, String fileUrl) {
        String sql = "INSERT INTO Assignment (classroom_id, title, description, type, file_url, deadline, created_by) VALUES (?, ?, ?, 'assignment', ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, classroomId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, fileUrl);
            ps.setTimestamp(5, deadline);
            ps.setInt(6, createdBy);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Tạo thông báo (Announcement)
    public static boolean createAnnouncement(int classroomId, String title, String description, int createdBy) {
        String sql = "INSERT INTO Assignment (classroom_id, title, description, type, created_by) VALUES (?, ?, ?, 'announcement', ?)";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, classroomId);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setInt(4, createdBy);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
} 