package ayan.dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    public void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String pass = passwordField.getText();

        UserDAO userDAO = new UserDAO();
        if (userDAO.loginUser(email, pass)) {
            System.out.println("Login Successful ✅");
        } else {
            System.out.println("Invalid Email or Password ❌");
        }

    }

    public void openRegister(ActionEvent event) {
        System.out.println("Open Register Page...");
        // TODO: Switch scene to register.fxml
    }
}
