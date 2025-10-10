package ayan.dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    // 🔹 Login button action
    public void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String pass = passwordField.getText();

        UserDAO userDAO = new UserDAO();

        if (userDAO.loginUser(email, pass)) {
            System.out.println("✅ Login Successful!");

            try {
                // ✅ Dashboard.fxml load karo
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ayan/dev/view/dashboard.fxml"));
                Parent root = loader.load();

                // ✅ DashboardController me email bhejo
                DashboardController controller = loader.getController();
                controller.setUserEmail(email);

                // ✅ Scene switch karo
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Dashboard - Online Banking System");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("❌ Invalid Email or Password");
        }
    }

    // 🔹 Register button action
    public void openRegister(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ayan/dev/view/register.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Register - Online Banking");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
