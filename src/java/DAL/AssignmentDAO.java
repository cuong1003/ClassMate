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
                assignments.add(new Assignment(assignmentId, classId, title, des, createdBy, createdAt, deadLine));
            }
            rs.close();
            ps.close();
            conn.close();   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
    public void addAssignment(String ccode, String title, String description, int createdBy, Date deadline) throws Exception {
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
            
            //Insert bài tập
            String insertSql = "INSERT INTO Assignment (classroom_id, title, description, created_by, created_at, deadline) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(insertSql);
            psInsert.setInt(1, classroomId);  // Sử dụng classroom_id (int)
            psInsert.setString(2, title);
            psInsert.setString(3, description);
            psInsert.setInt(4, createdBy);
            psInsert.setDate(5, new java.sql.Date(System.currentTimeMillis())); // created_at = thời gian hiện tại
            psInsert.setDate(6, deadline);  // deadline
            
            psInsert.executeUpdate();
            psInsert.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi thêm bài tập: " + e.getMessage());
        }
    }
} 