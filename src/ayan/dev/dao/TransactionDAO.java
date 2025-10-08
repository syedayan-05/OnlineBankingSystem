package ayan.dev.dao;

import java.sql.*;
import ayan.dev.utils.DBConnection;

public class TransactionDAO {

    public boolean recordTransaction(String email, String type, double amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();

            // Get current balance
            double currentBalance = getCurrentBalance(email);

            double newBalance = type.equals("deposit")
                    ? currentBalance + amount
                    : currentBalance - amount;

            // Update balance in user table
            String updateQuery = "UPDATE users SET balance = ? WHERE email = ?";
            ps = conn.prepareStatement(updateQuery);
            ps.setDouble(1, newBalance);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();

            // Record transaction
            String insertQuery = "INSERT INTO transactions (user_email, type, amount, balance) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(insertQuery);
            ps.setString(1, email);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.setDouble(4, newBalance);
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (ps != null) ps.close(); if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

    public double getCurrentBalance(String email) {
        double balance = 0.0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT balance FROM users WHERE email = ?")) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
}
