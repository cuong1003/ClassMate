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
    // Lấy danh sách bài tập bằng classcode 
    public List<Assignment> getAssignByCcode(String ccode) throws Exception {
        List<Assignment> assignments = new ArrayList<>();
        //Order by id desc để hiển thị bài tập mới nhất ở đầu.
        String sql = "SELECT a.* FROM Assignment AS a INNER JOIN Classroom AS c ON a .classroom_id = c.id WHERE c .class_code =  ? ORDER BY a.id DESC";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (ccode != null) {
                ps.setString(1, ccode.trim());
            } else {
                ps.setString(1, "");
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int assignmentId = rs.getInt("id");
                int classId = rs.getInt("classroom_id");
                String title = rs.getString("title");
                String des = rs.getString("description");
                int createdBy = rs.getInt("created_by");
                Date createdAt = rs.getDate("created_at");
                Date deadLine = rs.getDate("deadline");
                String fileUrl = rs.getString("file_url"); // Lấy Google Drive URL
                assignments.add(new Assignment(assignmentId, classId, title, des, createdBy, createdAt, deadLine, fileUrl));
            }
            rs.close();
            ps.close();
            conn.close();   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
    // Method cũ - giữ nguyên để backward compatible
    public void addAssignment(String ccode, String title, String description, int createdBy, Date deadline) throws Exception {
        addAssignmentWithFile(ccode, title, description, createdBy, deadline, null);
    }
    
    // Method mới - hỗ trợ Google Drive URL
    public void addAssignmentWithFile(String ccode, String title, String description, int createdBy, Date deadline, String fileUrl) throws Exception {
        //Lấy classroom_id từ class_code
        String getClassIdSql = "SELECT id FROM Classroom WHERE class_code = ?";
        int classroomId = -1;
        
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            
            //Lấy classroom_id
            PreparedStatement psGetId = conn.prepareStatement(getClassIdSql);
            psGetId.setString(1, ccode);
            ResultSet rs = psGetId.executeQuery();
            
            if (rs.next()) {
                classroomId = rs.getInt("id");
            } else {
                rs.close();
                psGetId.close();
                conn.close();
                throw new Exception("Không tìm thấy lớp học với mã: " + ccode);
            }
            
            rs.close();
            psGetId.close();
            
            //Insert bài tập với Google Drive URL
            String insertSql = "INSERT INTO Assignment (classroom_id, title, description, created_by, created_at, deadline, file_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(insertSql);
            psInsert.setInt(1, classroomId);  // Sử dụng classroom_id (int)
            psInsert.setString(2, title);
            psInsert.setString(3, description);
            psInsert.setInt(4, createdBy);
            psInsert.setDate(5, new java.sql.Date(System.currentTimeMillis())); // created_at = thời gian hiện tại
            psInsert.setDate(6, deadline);  // deadline
            psInsert.setString(7, fileUrl); // Google Drive URL (có thể null)
            
            psInsert.executeUpdate();
            psInsert.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi thêm bài tập: " + e.getMessage());
        }
    }
    
    // Method lấy bài tập theo ID (để xem chi tiết)
    public Assignment getAssignmentById(int assignmentId) throws Exception {
        String sql = "SELECT * FROM Assignment WHERE id = ?";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, assignmentId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int classId = rs.getInt("classroom_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int createdBy = rs.getInt("created_by");
                Date createdAt = rs.getDate("created_at");
                Date deadline = rs.getDate("deadline");
                String fileUrl = rs.getString("file_url");
                
                rs.close();
                ps.close();
                conn.close();
                
                return new Assignment(assignmentId, classId, title, description, createdBy, createdAt, deadline, fileUrl);
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi lấy bài tập: " + e.getMessage());
        }
        return null;
    }
    
    // Method cập nhật Google Drive URL cho bài tập đã có
    public void updateAssignmentFileUrl(int assignmentId, String fileUrl) throws Exception {
        String sql = "UPDATE Assignment SET file_url = ? WHERE id = ?";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fileUrl);
            ps.setInt(2, assignmentId);
            
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi cập nhật file URL: " + e.getMessage());
        }
    }
} 