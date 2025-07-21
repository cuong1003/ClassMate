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

    // Lấy ClassroomId từ ClassCode
    public static int getClassroomId(String ccode) {
        String sql = "SELECT id FROM Classroom WHERE class_code = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ccode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Lấy danh sách bài tập của lớp (ShowAssignment.jsp)
    public static List<Assignment> getAssignmentsList(String ccode) {
        String sql = "SELECT a.id, a.title, a.description, a.file_url, a.created_at, a.deadline, a.created_by "
                + "FROM Assignment a "
                + "INNER JOIN Classroom c ON a.classroom_id = c.id "
                + "WHERE c.class_code = ? AND a.type = 'assignment' "
                + "ORDER BY a.created_at DESC";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ccode);
            ResultSet rs = ps.executeQuery();
            List<Assignment> assignmentList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String fileUrl = rs.getString("file_url");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp deadline = rs.getTimestamp("deadline");
                int createBy = rs.getInt("created_by");
                Assignment assignment = new Assignment(id, title, description, fileUrl, createdAt, deadline, createBy);
                assignmentList.add(assignment);
            }
            return assignmentList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Lấy danh sách thông báo (Announcement cho ClassManagement.jsp)
    public static List<Assignment> getAnnouncementsList(String ccode) {
        String sql = "SELECT a.title, a.description, a.created_at, a.created_by "
                + "FROM Assignment a "
                + "INNER JOIN Classroom c ON a.classroom_id = c.id "
                + "WHERE c.class_code = ? AND a.type = 'announcement' "
                + "ORDER BY a.created_at DESC";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ccode);
            ResultSet rs = ps.executeQuery();
            List<Assignment> announcementList = new ArrayList<>();
            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                Timestamp createdAt = rs.getTimestamp("created_at");
                int createBy = rs.getInt("created_by");
                // Sử dụng constructor có sẵn và set type sau đó
                Assignment announcement = new Assignment(title, description, createdAt, createBy);
                announcement.setType("announcement");
                announcementList.add(announcement);
            }
            return announcementList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Lấy toàn bộ thông tin của bài tập đó theo ID bài tập
    public static Assignment getAssigmentById(int id) {
        String sql = "SELECT id, classroom_id,  \n"
                + "title ,description, file_url, deadline, \n"
                + "created_at FROM Assignment WHERE id = ?";
        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idd = rs.getInt("id");
                int class_id = rs.getInt("classroom_id");
                String title = rs.getString("title");
                String des = rs.getString("description");
                String url = rs.getString("file_url");
                Timestamp deadline = rs.getTimestamp("deadline");
                Timestamp created_at = rs.getTimestamp("created_at");
                return new Assignment(idd, class_id, title, des, url, deadline, created_at);
            }
        } catch (Exception e) {
        }

        return null;
    }

}
