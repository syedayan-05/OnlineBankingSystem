package ayan.dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class DashboardController {

    @FXML
    private Label userNameLabel;

    private String currentUserEmail;
    private double balance = 0.0; // Temporary — later connect with DB

    // Ye method LoginController se call hoga user ka naam/email set karne ke liye
    public void setUserData(String userEmail) {
        this.currentUserEmail = userEmail;
        userNameLabel.setText("Welcome, " + userEmail + " 👋");
    }

    @FXML
    public void handleDeposit(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit Money");
        dialog.setHeaderText("💰 Deposit Funds");
        dialog.setContentText("Enter amount to deposit:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0) {
                    balance += amount;
                    showAlert("Deposit Successful ✅", "New Balance: ₹" + balance, Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Invalid Amount", "Please enter a positive value.", Alert.AlertType.WARNING);
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a valid number.", Alert.AlertType.ERROR);
            }
        });
    }

    @FXML
    public void handleWithdraw(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Withdraw Money");
        dialog.setHeaderText("💸 Withdraw Funds");
        dialog.setContentText("Enter amount to withdraw:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    showAlert("Withdraw Successful ✅", "Remaining Balance: ₹" + balance, Alert.AlertType.INFORMATION);
                } else if (amount > balance) {
                    showAlert("Insufficient Balance ❌", "You don't have enough funds.", Alert.AlertType.WARNING);
                } else {
                    showAlert("Invalid Amount", "Please enter a positive value.", Alert.AlertType.WARNING);
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a valid number.", Alert.AlertType.ERROR);
            }
        });
    }

    @FXML
    public void handleCheckBalance(ActionEvent event) {
        showAlert("Current Balance 💳", "Your balance is: ₹" + balance, Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        try {
            ayan.dev.SceneSwitcher.switchScene(event, "/ayan/dev/view/login.fxml", "Online Banking - Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Common method for alerts
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
