package ayan.dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    public void handleRegister(ActionEvent event) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // 🧠 Basic validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Missing Fields", "Please fill all fields before registering.", Alert.AlertType.WARNING);
            return;
        }

        // ✅ Register user
        UserDAO userDAO = new UserDAO();
        userDAO.registerUser(name, email, password);
        System.out.println("User Registered Successfully ✅");

        // 🟢 Switch to Dashboard
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ayan/dev/view/dashboard.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard - Online Banking System");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 💬 Reusable alert
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
