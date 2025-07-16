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
import java.sql.SQLException;

/**
 * ClassroomDAO for ClassMate system
 */
public class ClassroomDAO {

    // TODO: Add CRUD methods for Classroom
    // Tạo lớp học
    // Tạo lớp trả về ID để thực hiện một số cộng dụng khác... Tạo folder luu data của lớp theo ID,...
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

    //tham gia lớp học
    public boolean joinClassroom(int userId, String classCode) throws Exception {
        String findClassSql = "SELECT id FROM Classroom WHERE class_code = ?";
        String checkExistSql = "SELECT COUNT(*) FROM ClassroomMember WHERE user_id = ? AND classroom_id = ?";
        String insertSql = "INSERT INTO ClassroomMember (user_id, classroom_id, joined_at) VALUES (?, ?, GETDATE())";

        try (Connection conn = new DBContext().getConnection()) {
            // 1. Tìm classroom_id
            int classroomId = -1;
            try (PreparedStatement stmt = conn.prepareStatement(findClassSql)) {
                stmt.setString(1, classCode);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    classroomId = rs.getInt("id");
                } else {
                    return false; // không tìm thấy class
                }
            }

            // 2. Kiểm tra đã tham gia chưa
            try (PreparedStatement stmt = conn.prepareStatement(checkExistSql)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, classroomId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // đã tham gia rồi
                }
            }

            // 3. Chèn dữ liệu
            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, classroomId);
                stmt.executeUpdate();
                return true; // thành công
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //get Id class by class code
    public int getClassroomIdByCode(String classCode) {
        String sql = "SELECT id FROM [Classroom] WHERE class_code = ?";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, classCode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                rs.close();
                ps.close();
                conn.close();
                return id;
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
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

    public List<Classroom> getClassByUserId(int userId) {
        List<Classroom> classList = new ArrayList<>();
        String sql = "SELECT \n"
                + "    c.id, \n"
                + "    c.class_name, \n"
                + "    c.class_code,\n"
                + "    c.created_by\n"
                + "FROM \n"
                + "    Classroom c\n"
                + "JOIN \n"
                + "    ClassroomMember cm ON c.id = cm.classroom_id\n"
                + "WHERE \n"
                + "    cm.user_id = ?;";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int classroomId = rs.getInt("id");
                String className = rs.getString("class_name");
                String classCode = rs.getString("class_code");
                int createBy = rs.getInt("created_by");
                classList.add(new Classroom(classroomId, className, classCode, createBy));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classList;

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
