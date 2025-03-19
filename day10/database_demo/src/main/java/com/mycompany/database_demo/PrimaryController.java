package com.mycompany.database_demo;


import com.mycompany.dal.DAO;
import com.mycompany.dal.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {
    

    @FXML
    private Button primaryButton;
    
    @FXML
    private Button login_btn;

    @FXML
    private TextField passwd_field;

    @FXML
    private Button reg_btn;

    @FXML
    private TextField user_field;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    
    @FXML
    private void handleLogin() {
        String username = user_field.getText();
        String password = passwd_field.getText();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            return;
        }

        try {
            UserDTO user = new UserDTO(username, password);
            boolean success = DAO.login(user);
        } catch (SQLException e) {
        }
    }
    
    @FXML
    private void handleRegister() {
        String username = user_field.getText();
        String password = passwd_field.getText();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            return;
        }

        try {
            UserDTO user = new UserDTO(username, password);
            int result = DAO.register(user);
        } catch (SQLException e) {
        }
    }
}
