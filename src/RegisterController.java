import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.lang.Thread.State;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.Action;

public class RegisterController {

    @FXML
    private Button closeButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Button registerButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField setPasswordField;

    /* Checks to see if the 2 passwords match, and if they
       do match allows for teh account to be registered. 
       and then adds the information to the database. */

    public void registerButtonOnAction(ActionEvent event) {
        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
        }
        else {
            confirmPasswordLabel.setText("Passwords do no match!");
        }
        
    }

    /* Close Button code so that the application can be exited. */

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        /* Stores the users input into the variables. */

        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = setPasswordField.getText();

        /* Inserts the stores variables into the database. */
        
        String insertFields = "INSERT INTO user_account(username, email, password) VALUES ('";
        String insertValues = username + "','" + email + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        /* Try and catch statement to help catch any errors. */

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel.setText("User has been registered successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}



