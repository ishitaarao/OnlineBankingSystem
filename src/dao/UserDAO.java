package dao;

import model.Admin;
import model.Customer;
import model.User;
import util.DBConnection;
import util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** All database operations for the users table. UI code never writes SQL directly. */
public class UserDAO {

    /** Inserts a new user. Returns the generated user_id. */
    public int register(String fullName, String email, String username, String password, String role)
            throws SQLException {
        String sql = "INSERT INTO users (full_name, email, username, password_hash, role) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, PasswordUtil.hash(password));
            ps.setString(5, role);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    /** Returns the user if username and password match, otherwise null. */
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, PasswordUtil.hash(password));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    /** Returns every user (used by Admin > User Management). */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) users.add(mapRow(rs));
        }
        return users;
    }

    /** Updates name and email (Customer > Profile Management, Admin > edit user). */
    public boolean updateProfile(int userId, String fullName, String email) throws SQLException {
        String sql = "UPDATE users SET full_name = ?, email = ? WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setInt(3, userId);
            return ps.executeUpdate() > 0;
        }
    }

    /** Deletes a user (Admin > User Management). */
    public boolean deleteUser(int userId) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE user_id = ?")) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        }
    }

    /** Converts one row into an Admin or Customer object. */
    private User mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String name = rs.getString("full_name");
        String email = rs.getString("email");
        String username = rs.getString("username");
        String hash = rs.getString("password_hash");
        return "ADMIN".equals(rs.getString("role"))
                ? new Admin(id, name, email, username, hash)
                : new Customer(id, name, email, username, hash);
    }
}
