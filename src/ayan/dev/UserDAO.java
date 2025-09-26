package ayan.dev;

import java.sql.*;

public class UserDAO {
    private static Connection conn;

    public UserDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking", "root", "Gv9@K$3rT!e#8zWqP^nL0xV&");
            System.out.println("DataBase Connect successfully!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Register User
//    public static void registerUser(String name, String email, String password) {
//        String query = "INSERT INTO users (name, email, password, balance) VALUES (?, ?, ?, 0.0)";
//        try (PreparedStatement ps = conn.prepareStatement(query)) {
//            ps.setString(1, name);
//            ps.setString(2, email);
//            ps.setString(3, password);
//            ps.executeUpdate();
//            System.out.println("✅ User Registered Successfully!");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    // ✅ Login User
    public static boolean loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email=? AND password=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("✅ Login Successful!");
                return true;
            } else {
                System.out.println("❌ Invalid email or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    /register
    public static void registerUser(String name, String email, String password) {
        String query = "INSERT INTO users (name, email, password, balance) VALUES (?, ?, ?, 0.0)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            System.out.println("✅ User Registered Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Deposit
    public void deposit(String email, double amount) {
        String query = "UPDATE users SET balance = balance + ? WHERE email=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, amount);
            ps.setString(2, email);
            ps.executeUpdate();
            System.out.println("✅ Deposit Successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Withdraw
    public void withdraw(String email, double amount) {
        String checkQuery = "SELECT balance FROM users WHERE email=?";
        try (PreparedStatement ps = conn.prepareStatement(checkQuery)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance >= amount) {
                    String updateQuery = "UPDATE users SET balance = balance - ? WHERE email=?";
                    try (PreparedStatement ups = conn.prepareStatement(updateQuery)) {
                        ups.setDouble(1, amount);
                        ups.setString(2, email);
                        ups.executeUpdate();
                        System.out.println("✅ Withdrawal Successful!");
                    }
                } else {
                    System.out.println("❌ Insufficient Balance!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Check Balance
    public void checkBalance(String email) {
        String query = "SELECT balance FROM users WHERE email=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("💰 Current Balance: " + rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


