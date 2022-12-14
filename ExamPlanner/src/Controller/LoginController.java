package Controller;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import javafx.stage.Window;
import utils.ConnectionUtil;

public class LoginController {


    @FXML
    private Button submitButton;
    
    
    
    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;


    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }

        String email = emailIdField.getText();
        String password = passwordField.getText();

        ConnectionUtil jdbcDao = new ConnectionUtil();
        boolean flag = jdbcDao.validate(email, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
            
            Stage stagebis = (Stage) submitButton.getScene().getWindow();
        	
            stagebis.close();
    	    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/MainPage.fxml"));
    	    Parent root1 = (Parent) fxmlLoader.load();
    	    Stage stage = new Stage();
    	    stage.setScene(new Scene(root1,1050,710));  
    	    stage.show();
    	
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
