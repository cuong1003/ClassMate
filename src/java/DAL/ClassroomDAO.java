package DAL;
import Models.Classroom;
import Models.Users;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    public List<Classroom> getClassByOwnerId(int ownerId) {
        //Note: Sẽ join thêm để lấy số lượng sinh viên có trong lớp đó của giáo viên.
        //Trạng thái: chưa làm.
        List<Classroom> classList = new ArrayList<>();
        String sql = "SELECT * FROM [Classroom] WHERE created_by = ?";
        try{
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                  int classId = rs.getInt("id");
                  String className = rs.getString("class_name");
                  String classCode = rs.getString("class_code");
                  int createBy = rs.getInt("created_by");
                  classList.add(new Classroom(classId, className, classCode, createBy));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;
        

    }
} 