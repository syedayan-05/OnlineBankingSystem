package ayan.dev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    public void handleRegister(ActionEvent event) {
        User user =new User(0,nameField.getText(),emailField.getText(),passwordField.getText());
        UserDAO.registerUser(user);
        System.out.println("User Registered Successfully ");
    }
}
