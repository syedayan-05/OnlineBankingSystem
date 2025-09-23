package ayan.dev;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {
    public static void registerUser(User user){
        try {
            Connection connection= DBConnection.getConnection();
            String query = "INSERT INTO user(name,email,password) VALUES(?,?,?)";
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1,user.name);
            ps.setString(2,user.email);
            ps.setString(3,user.password);

            ps.executeUpdate();
            System.out.println("User Registered Successfully!! :) ");
        } catch (Exception e) {
            System.out.println("Error : " +e.getMessage());
        }
    }
}
