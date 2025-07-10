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
        String sql = "SELECT a.* FROM Assignment AS a INNER JOIN Classroom AS c ON a .classroom_id = c.id WHERE c .class_code =  ?";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ccode);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int assignmentId = rs.getInt("assignment_id");
                int classId = rs.getInt("classroom_id");
                String title = rs.getString("title");
                String des = rs.getString("des");
                int createdBy = rs.getInt("created_by");
                Date createdAt = rs.getDate("created_at");
                Date deadLine = rs.getDate("dead_line");
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
} 