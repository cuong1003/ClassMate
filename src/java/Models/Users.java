/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author c9
 */
public class Users {
    private int userId;
    private String user;
    private String pass;
    private String fullname;
    private String email;
    private int role;

    public Users() {
    }

    public Users(int userId, String user, String pass,String fullname ,String email, int role) {
        this.userId = userId;
        this.user = user;
        this.pass = pass;
        this.email = email;
        this.role = role;
        this.fullname = fullname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

     

    @Override
    public String toString() {
        return "Users{" + "user=" + user + ", pass=" + pass + ", email=" + email + ", roll=" + role + '}';
    }
    
    
}
