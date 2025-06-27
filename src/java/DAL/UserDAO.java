package DAL;

import Models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * UserDAO for ClassMate system
 */
public class UserDAO {
    // TODO: Add CRUD methods for User
    public Users checkLogin(String username, String password, String role) {
        String sql = "SELECT * FROM [User] WHERE username = ? AND password = ? AND role = ?";

        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String user = rs.getString("username");
                String pass = rs.getString("password");
                int roll = rs.getInt("role");

                rs.close();
                ps.close();
                conn.close();

                return new Users(user, pass, roll);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;//sai tk hoac mk
    }
} 