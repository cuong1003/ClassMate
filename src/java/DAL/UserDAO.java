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
    public Users GetUserById(int userId) {
        String sql = "Select us.id, us.fullname, us.email, us.role\n"
                + "From [ClassMate].[dbo].[User] us\n"
                + "where id = ?";
        Users us = null;
        try {
            DBContext db = new DBContext();
            Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                us = new Users();
                us.setUserId(rs.getInt(1));
                us.setFullname(rs.getNString(2));
                us.setEmail(rs.getString(3));
                us.setRole(rs.getInt(4));
                return us;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return us;
    }

    public void updateUserInfo(int id, String fullname, String email) {
    String sql = "UPDATE [User] SET fullname = ?, email = ? WHERE id = ?";
    try {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setNString(1, fullname);
        ps.setString(2, email);
        ps.setInt(3, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    }
