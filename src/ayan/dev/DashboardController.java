package ayan.dev;

import ayan.dev.dao.TransactionDAO;
import ayan.dev.utils.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Label;
import java.util.Optional;

public class DashboardController {

    @FXML
    private Label userNameLabel;

    private String userEmail;

    private final TransactionDAO transactionDAO = new TransactionDAO();

    public void setUserEmail(String email) {
        this.userEmail = email;
        double balance = transactionDAO.getCurrentBalance(email);
        userNameLabel.setText("Welcome, " + email + " | Balance: ₹" + balance);
    }

    @FXML
    private void handleDeposit(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit Money");
        dialog.setHeaderText("Enter amount to deposit:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                transactionDAO.recordTransaction(userEmail, "deposit", amount);
                updateBalanceLabel();
                showAlert("Deposit Successful ✅", "₹" + amount + " added to your account.");
            } catch (NumberFormatException e) {
                showAlert("Invalid Input ❌", "Please enter a valid amount.");
            }
        });
    }

    @FXML
    private void handleWithdraw(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Withdraw Money");
        dialog.setHeaderText("Enter amount to withdraw:");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                double current = transactionDAO.getCurrentBalance(userEmail);
                if (amount > current) {
                    showAlert("Insufficient Balance ❌", "You don't have enough balance.");
                    return;
                }
                transactionDAO.recordTransaction(userEmail, "withdraw", amount);
                updateBalanceLabel();
                showAlert("Withdrawal Successful ✅", "₹" + amount + " withdrawn successfully.");
            } catch (NumberFormatException e) {
                showAlert("Invalid Input ❌", "Please enter a valid amount.");
            }
        });
    }

    private void updateBalanceLabel() {
        double newBalance = transactionDAO.getCurrentBalance(userEmail);
        userNameLabel.setText("Welcome, " + userEmail + " | Balance: ₹" + newBalance);
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    @FXML
    private void handleCheckBalance(ActionEvent event) {
        double balance = transactionDAO.getCurrentBalance(userEmail);
        showAlert("Your Current Balance", "₹" + balance);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            SceneSwitcher.switchScene(event, "/ayan/dev/view/login.fxml", "Login - Online Banking");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
