package ayan.dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    // 🔹 Login button ka action
    public void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String pass = passwordField.getText();

        UserDAO userDAO = new UserDAO();
        if (userDAO.loginUser(email, pass)) {
            System.out.println("Login Successful ✅");

            // ✅ Login success hone ke baad dashboard.fxml open karo
            try {
                ayan.dev.SceneSwitcher.switchScene(event, "/ayan/dev/view/dashboard.fxml", "Dashboard - Online Banking System");


                // Current stage nikaalo
//                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//                stage.setScene(scene);
//                stage.setTitle("Dashboard - Online Banking System");
//                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Invalid Email or Password ❌");
        }
    }

    // 🔹 Register button ka action
    public void openRegister(ActionEvent event) {
        try {
            ayan.dev.SceneSwitcher.switchScene(event, "/ayan/dev/view/register.fxml", "Register - Online Banking");

//            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.setTitle("Register - Online Banking System");
//            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
