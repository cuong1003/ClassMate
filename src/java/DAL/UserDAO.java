package DAL;

import Models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                int userId = rs.getInt("id");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String fullname = rs.getString("fullname");
                String email = rs.getString("email");
                int roleuser = rs.getInt("role");

                rs.close();
                ps.close();
                conn.close();

                return new Users(userId, user, pass, fullname , email, roleuser);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;//sai tk hoac mk
    }
    public void register(String username, String password, String fullname, String email, int role) {
        String sql = "INSERT INTO [User] (username, password, fullname, email, role) VALUES (?, ?, ?, ?, ?)";
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, email);
            ps.setInt(5, role);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE username = ?";
        try {
            DBContext db = new DBContext();
        
            Connection conn = db.getConnection(); // Your method to get DB connection
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
} 