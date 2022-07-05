import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.Action;

public class UserPageController {

    @FXML
    private TextField benchTextField;

    @FXML
    private Button closeButton;

    @FXML
    private TextField deadliftTextField;

    @FXML
    private Button previousPageButton;

    @FXML
    private TextField squatTextField;

    @FXML
    private Button submitDetailsButton;

    @FXML
    private Label statsLabel;
    
    public void submitDetailsOnAction(ActionEvent event) {
        submitDetailsToDatabase();
    }

    private void submitDetailsToDatabase() {
        
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String squat = squatTextField.getText();
        String bench = benchTextField.getText();
        String deadlift = deadliftTextField.getText();

        String insertFields = "INSERT INTO user_stats(squat, bench, deadlift) VALUES ('";
        String insertValues = squat + "','" + bench + "','" + deadlift + "')";
        String insertToStats = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToStats);

            statsLabel.setText("User stats have been saved!");
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void previousPage(ActionEvent event) throws IOException  {
        loginForm();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        
    }

    public void loginForm() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root));
            registerStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}
