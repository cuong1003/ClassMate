package DAL;

import Models.Classroom;
import Models.ClassroomMember;
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
    // Tạo lớp học
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

    //Load danh sách lớp được thảo với id giáo viên.
    public List<Classroom> getClassByOwnerId(int ownerId) {
        //Note: Sẽ join thêm để lấy số lượng sinh viên có trong lớp đó của giáo viên.
        //Trạng thái: chưa làm.
        List<Classroom> classList = new ArrayList<>();
        String sql = "SELECT * FROM [Classroom] WHERE created_by = ?";
        try {
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
    //Load danh sách thành viên trong lớp theo ccode.
           // DUC HUY DUC HUY DUC HUY DUC 
    public String getTeacherFullnameByClassCode(String classCode) {
        String sql = "SELECT u.fullname "
                + "FROM Classroom c "
                + "JOIN [User] u ON c.created_by = u.id "
                + "WHERE c.class_code = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, classCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Không tìm thấy giáo viên";
    }

    public String getEmailByUserId(int userId) {
        String sql = "SELECT email FROM [User] WHERE id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không có email";
    }

    public String getFullnameByUserId(int userId) {
        String sql = "SELECT fullname FROM [User] WHERE id = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không tìm thấy";
    }

    public List<ClassroomMember> loadClassMembers(String classCode) {
        List<ClassroomMember> members = new ArrayList<>();
        String sql = "SELECT cm.id, cm.user_id, cm.classroom_id, cm.joined_at "
                + "FROM ClassroomMember cm "
                + "JOIN Classroom c ON cm.classroom_id = c.id "
                + "WHERE c.class_code = ?";

        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, classCode);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClassroomMember cm = new ClassroomMember();
                cm.setId(rs.getInt("id"));
                cm.setUser_id(rs.getInt("user_id"));
                cm.setClassroom_id(rs.getInt("classroom_id"));
                cm.setJoined_time(rs.getTimestamp("joined_at").toLocalDateTime());

                members.add(cm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return members;
    }

}
