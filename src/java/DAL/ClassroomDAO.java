package DAL;
import Models.Classroom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * ClassroomDAO for ClassMate system
 */
public class ClassroomDAO {
    // TODO: Add CRUD methods for Classroom
    public void createClass(String className, String classCode, int ownerId) {
        String sql = "INSERT INTO [Classroom] (class_name, class_code, created_by) VALUES (?, ?, ?)";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, className);
            ps.setString(2, classCode);
            ps.setInt(3, ownerId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 