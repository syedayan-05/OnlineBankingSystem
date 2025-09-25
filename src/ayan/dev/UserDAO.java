package ayan.dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static void registerUser(User user){
        try {
            Connection connection= DBConnection.getConnection();
            String query = "INSERT INTO user(name,email,password) VALUES(?,?,?)";
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1,user.name);
            ps.setString(2,user.email);
            ps.setString(3,user.password);

            int row = ps.executeUpdate();
            if (row > 0) {
                System.out.println("User Registered Successfully!! :) ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Login User
    public static boolean loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful 🎉");
                return true;
            } else {
                System.out.println("Invalid email or password ❌");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
