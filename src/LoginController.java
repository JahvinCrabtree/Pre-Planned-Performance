import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.net.URL;


public class LoginController {

    public LoginController() {

    }

    @FXML
    private Label errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button registrationButton;

    @FXML
    private Button trainingPlanButton;



    public void userLogin(ActionEvent event) throws IOException {
        checkLogIn();   
        userPageForm();

    }

    public void registrationPage(ActionEvent event) throws IOException {
        createAccountForm();
    }

    public void trainingPage(ActionEvent event) throws IOException {
        trainingPlanForm();
    }
    
    private void checkLogIn() throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";
       

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin); 

            while(queryResult.next()){
                if(queryResult.getInt(1)== 1) {
                    errorMessage.setText("Login Successful.");
                }
                else {
                    errorMessage.setText("Incorrect username or password, try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();
            

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void trainingPlanForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("TrainingPlan.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void userPageForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
