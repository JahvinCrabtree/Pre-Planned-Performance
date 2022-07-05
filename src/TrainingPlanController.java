import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Random;

public class TrainingPlanController implements Initializable{

    @FXML
    private TextField benchTextField;

    @FXML
    private TextField deadliftTextField;

    @FXML
    private Button getTrainingPlanButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private TextField squatTextField;

    @FXML
    private TextArea trainingPlanTextArea;

    @FXML
    private ComboBox<String> typeOfTrainingComboBox;
    
    @FXML
    private ComboBox<Integer> daysComboBox;

    @FXML
    private Label errorLabel;

    @FXML
    private Button closeButton;
    

    public static int randInt(int min, int max) {
        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;
    
        return randomNum;
    }

    public void previousPage(ActionEvent event) throws IOException  {
        loginForm();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        
    }
    
    public void getTrainingPlanButtonOnAction(ActionEvent event) throws IOException {
        createTrainingPlan();
    }
    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
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

    public void createTrainingPlan() {
        try {
        int squat = Integer.parseInt(squatTextField.getText());
        int bench = Integer.parseInt(benchTextField.getText());
        int deadlift = Integer.parseInt(deadliftTextField.getText());
            errorLabel.setText("");
        }
        catch(NumberFormatException e) {
            errorLabel.setText("Numbers only please.");
            
        }
        catch(Exception e) {
            System.out.println(e);
        }
        
        if(daysComboBox.getValue().equals(1) && typeOfTrainingComboBox.getValue().equalsIgnoreCase("Bodybuilding"))  {
           
            int squat = Integer.parseInt(squatTextField.getText()); 
            int bench = Integer.parseInt(benchTextField.getText());
            int deadlift = Integer.parseInt(deadliftTextField.getText());
            squat = (int) (squat*70*0.01);
            bench = (int) (bench*70*0.01); 
            deadlift = (int) (deadlift*70*0.01); 
            
            trainingPlanTextArea.setText("You're training weight for Squat, bench and deadlift will be S" + String.valueOf(squat) +" B"+ String.valueOf(bench) +" D"+ String.valueOf(deadlift) + " What you're going to "+ "\n" + "want to do is" + 
            " between 3-6 per exercise for optimum hypertrophy gains, this will be" + "\n" + "performed as so the format will be SETS then REPETITIONS."+  "\n"+ "\n" +
            "Perform 4 sets of 10 with the working weight of " + String.valueOf(squat) + " for Squats."+ "\n" +
            "Perform 4 sets of 10 with the working weight of " + String.valueOf(bench) + " for Bench."+ "\n" +
            "Perform 4 sets of 10 with the working weight of " + String.valueOf(deadlift) + " for Deadlift."); // These values stay 4 By 10 because it's just training 1 time a week and can't be undulated. 
        }
        else if (daysComboBox.getValue().equals(3) && typeOfTrainingComboBox.getValue().equalsIgnoreCase("Bodybuilding")) {
            
            int squat = Integer.parseInt(squatTextField.getText()); 
            int bench = Integer.parseInt(benchTextField.getText());
            int deadlift = Integer.parseInt(deadliftTextField.getText());
            squat = (int) (Math.round(squat*0.7));
            bench = (int) (Math.round(bench*0.7)); 
            deadlift = (int) (Math.round(deadlift*0.7)); 
            int squatDayOneSet = randInt(3,4);
            int squatDayOneReps = randInt(8,10);
            int benchDayOneSet = randInt(3,4);
            int benchDayOneReps = randInt(8,10);
            int deadliftDayOneSet = randInt(3,4);
            int deadliftDayOneReps = randInt(8,10);
            
            trainingPlanTextArea.setText("You're training weight for Squat, bench and deadlift will be S" + String.valueOf(squat) +" B"+ String.valueOf(bench) +" D"+ String.valueOf(deadlift) +
            " What you're going to"+ "\n" +"want to do is, undualte each exercise as to continue to build the muscle." + "\n" + 
            "The format of this training plan will be in SETS then REPETITIONS" + "\n" +
            "\n" + "On day 1 Perform " +  String.valueOf(squatDayOneSet) + " Sets of " + String.valueOf(squatDayOneReps) + " With the working weight of " + String.valueOf(squat) + " for Squat." +
            "\n" + "On day 1 Perform " +  String.valueOf(benchDayOneSet) + " Sets of " + String.valueOf(benchDayOneReps) + " With the working weight of " + String.valueOf(bench) + " for Bench."+
            "\n" + "On day 1 Perform " +  String.valueOf(deadliftDayOneSet) + " Sets of " + String.valueOf(deadliftDayOneReps) + " With the working weight of " + String.valueOf(deadlift) + " for Deadlift."  
            + "\n"  
            + "\n" + "On day 2 Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(squat*1.1)) + " for Squat."
            + "\n" + "On day 2 Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(bench*1.1)) + " for Bench."
            + "\n" + "On day 2 Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.1))+ " for Deadlift."
            + "\n" 
            + "\n" + "On the final day Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(squat*1.2)) + " for Squat."
            + "\n" + "On the final day Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(bench*1.2)) + " for Bench."
            + "\n" + "On the final day Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.2)) + " for Deadlift."
            );
        }
        else if (daysComboBox.getValue().equals(5) && typeOfTrainingComboBox.getValue().equalsIgnoreCase("Bodybuilding")) {
            
            int squat = Integer.parseInt(squatTextField.getText());
            int bench = Integer.parseInt(benchTextField.getText());
            int deadlift = Integer.parseInt(deadliftTextField.getText());
            squat = (int) (Math.round(squat*0.7));
            bench = (int) (Math.round(bench*0.7)); 
            deadlift = (int) (Math.round(deadlift*0.7));
            int squatDayOneSet = randInt(4,6);
            int squatDayOneReps = randInt(8,10);
            int benchDayOneSet = randInt(4,6);
            int benchDayOneReps = randInt(8,10);
            int deadliftDayOneSet = randInt(4,6);
            int deadliftDayOneReps = randInt(8,10);

            trainingPlanTextArea.setText("You're training weight for Squat, bench and deadlift will be S" + String.valueOf(squat) +" B"+ String.valueOf(bench) +" D"+ String.valueOf(deadlift) +
            " What you're going to"+ "\n" +"want to do is, undualte each exercise as to continue to build the muscle." + "\n" + 
            "The format of this training plan will be in SETS then REPETITIONS" + "\n" +
            "\n" + "On day 1 Perform " +  String.valueOf(squatDayOneSet) + " Sets of " + String.valueOf(squatDayOneReps) + " With the working weight of " + String.valueOf(squat) + " for Squat." +
            "\n" + "On day 1 Perform " +  String.valueOf(benchDayOneSet) + " Sets of " + String.valueOf(benchDayOneReps) + " With the working weight of " + String.valueOf(bench) + " for Bench."+
            "\n" + "On day 1 Perform " +  String.valueOf(deadliftDayOneSet) + " Sets of " + String.valueOf(deadliftDayOneReps) + " With the working weight of " + String.valueOf(deadlift) + " for Deadlift."  
            + "\n"  
            + "\n" + "On day 2 Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(squat*1.1)) + " for Squat."
            + "\n" + "On day 2 Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(bench*1.1)) + " for Bench."
            + "\n" + "On day 2 Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.1))+ " for Deadlift."
            + "\n" 
            + "\n" + "On day 3 Perform " + String.valueOf(squatDayOneSet + 2) + " Sets of " + String.valueOf(squatDayOneReps - 3) + " With the working weight of " + String.valueOf(Math.round(squat*0.9)) + " for Squat."
            + "\n" + "On day 3 Perform " + String.valueOf(benchDayOneSet + 2) + " Sets of " + String.valueOf(benchDayOneReps - 3) + " With the working weight of " + String.valueOf(Math.round(bench*0.9)) + " for Bench."
            + "\n" + "On day 3 Perform " + String.valueOf(deadliftDayOneSet + 2) + " Sets of " + String.valueOf(deadliftDayOneReps - 3) + " With the working weight of " + String.valueOf(Math.round(deadlift*0.9)) + " for Deadlift."
            + "\n" 
            + "\n" + "On day 4 Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(squat*1.3)) + " for Squat."
            + "\n" + "On day 4 Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(bench*1.3)) + " for Bench."
            + "\n" + "On day 4 Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.3)) + " for Deadlift."
            + "\n" 
            + "\n" + "On the final day Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(squat*1.4)) + " for Squat."
            + "\n" + "On the final day Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(bench*1.4)) + " for Bench."
            + "\n" + "On the final day Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.4)) + " for Deadlift.");
        }
        else if (daysComboBox.getValue().equals(1) && typeOfTrainingComboBox.getValue().equalsIgnoreCase("Powerlifting")) {
            trainingPlanTextArea.setText("1 day PL");
        }
        else if (daysComboBox.getValue().equals(3) && typeOfTrainingComboBox.getValue().equalsIgnoreCase("Powerlifting")) {
            int squat = Integer.parseInt(squatTextField.getText());
            int bench = Integer.parseInt(benchTextField.getText());
            int deadlift = Integer.parseInt(deadliftTextField.getText());
            squat = (int) (Math.round(squat*0.8));
            bench = (int) (Math.round(bench*0.8)); 
            deadlift = (int) (Math.round(deadlift*0.75));
            int squatDayOneSet = randInt(5,7);
            int squatDayOneReps = randInt(3,8);
            int benchDayOneSet = randInt(5,7);
            int benchDayOneReps = randInt(3,8);
            int deadliftDayOneSet = randInt(5,7);
            int deadliftDayOneReps = randInt(3,8);
            
            trainingPlanTextArea.setText("You're training weight for Squat, bench and deadlift will be S" + String.valueOf(squat) +" B"+ String.valueOf(bench) +" D"+ String.valueOf(deadlift) +
            " What you're going to"+ "\n" +"want to do is, undualte each exercise as to continue to build the muscle." + "\n" + 
            "The format of this training plan will be in SETS then REPETITIONS" + "\n" +
            "\n" + "On day 1 Perform " +  String.valueOf(squatDayOneSet) + " Sets of " + String.valueOf(squatDayOneReps) + " With the working weight of " + String.valueOf(squat) + " for Squat." +
            "\n" + "On day 1 Perform " +  String.valueOf(benchDayOneSet) + " Sets of " + String.valueOf(benchDayOneReps) + " With the working weight of " + String.valueOf(bench) + " for Bench."+
            "\n" + "On day 1 Perform " +  String.valueOf(deadliftDayOneSet) + " Sets of " + String.valueOf(deadliftDayOneReps) + " With the working weight of " + String.valueOf(deadlift) + " for Deadlift."  
            + "\n"  
            + "\n" + "On day 2 Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(squat*1.3)) + " for Squat."
            + "\n" + "On day 2 Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(bench*1.3)) + " for Bench."
            + "\n" + "On day 2 Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 2) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.3))+ " for Deadlift."
            + "\n" 
            + "\n" + "On the final day Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(squat*1.2)) + " for Squat."
            + "\n" + "On the final day Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(bench*1.2)) + " for Bench."
            + "\n" + "On the final day Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.2)) + " for Deadlift."
            );
        }
        else if (daysComboBox.getValue().equals(5) && typeOfTrainingComboBox.getValue().equalsIgnoreCase("Powerlifting")) {
            int squat = Integer.parseInt(squatTextField.getText());
            int bench = Integer.parseInt(benchTextField.getText());
            int deadlift = Integer.parseInt(deadliftTextField.getText());
            squat = (int) (Math.round(squat*0.8));
            bench = (int) (Math.round(bench*0.8)); 
            deadlift = (int) (Math.round(deadlift*0.75));
            int squatDayOneSet = randInt(5,7);  
            int squatDayOneReps = randInt(3,8);
            int benchDayOneSet = randInt(5,7);
            int benchDayOneReps = randInt(3,8);
            int deadliftDayOneSet = randInt(5,7);
            int deadliftDayOneReps = randInt(3,8);

            trainingPlanTextArea.setText("You're training weight for Squat, bench and deadlift will be S" + String.valueOf(squat) +" B"+ String.valueOf(bench) +" D"+ String.valueOf(deadlift) +
            " What you're going to"+ "\n" +"want to do is, undualte each exercise as to continue to build the muscle." + "\n" + 
            "The format of this training plan will be in SETS then REPETITIONS" + "\n" +
            "\n" + "On day 1 Perform " +  String.valueOf(squatDayOneSet) + " Sets of " + String.valueOf(squatDayOneReps) + " With the working weight of " + String.valueOf(squat) + " for Squat." +
            "\n" + "On day 1 Perform " +  String.valueOf(benchDayOneSet) + " Sets of " + String.valueOf(benchDayOneReps) + " With the working weight of " + String.valueOf(bench) + " for Bench."+
            "\n" + "On day 1 Perform " +  String.valueOf(deadliftDayOneSet) + " Sets of " + String.valueOf(deadliftDayOneReps) + " With the working weight of " + String.valueOf(deadlift) + " for Deadlift."  
            + "\n"  
            + "\n" + "On day 2 Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps - 1) + " With the working weight of " + String.valueOf(Math.round(squat*1.1)) + " for Squat."
            + "\n" + "On day 2 Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps - 1) + " With the working weight of " + String.valueOf(Math.round(bench*1.1)) + " for Bench."
            + "\n" + "On day 2 Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + - 1 ) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.1))+ " for Deadlift."
            + "\n" 
            + "\n" + "On day 3 Perform " + String.valueOf(squatDayOneSet + 2) + " Sets of " + String.valueOf(squatDayOneReps - 2) + " With the working weight of " + String.valueOf(Math.round(squat*0.9)) + " for Squat."
            + "\n" + "On day 3 Perform " + String.valueOf(benchDayOneSet + 2) + " Sets of " + String.valueOf(benchDayOneReps - 2) + " With the working weight of " + String.valueOf(Math.round(bench*0.9)) + " for Bench."
            + "\n" + "On day 3 Perform " + String.valueOf(deadliftDayOneSet + 2) + " Sets of " + String.valueOf(deadliftDayOneReps - 2) + " With the working weight of " + String.valueOf(Math.round(deadlift*0.9)) + " for Deadlift."
            + "\n" 
            + "\n" + "On day 4 Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(squat*1.3)) + " for Squat."
            + "\n" + "On day 4 Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(bench*1.3)) + " for Bench."
            + "\n" + "On day 4 Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.3)) + " for Deadlift."
            + "\n" 
            + "\n" + "On the final day Perform " + String.valueOf(squatDayOneSet - 1) + " Sets of " + String.valueOf(squatDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(squat*1.4)) + " for Squat."
            + "\n" + "On the final day Perform " + String.valueOf(benchDayOneSet - 1) + " Sets of " + String.valueOf(benchDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(bench*1.4)) + " for Bench."
            + "\n" + "On the final day Perform " + String.valueOf(deadliftDayOneSet - 1) + " Sets of " + String.valueOf(deadliftDayOneReps + 1) + " With the working weight of " + String.valueOf(Math.round(deadlift*1.4)) + " for Deadlift.");
        }
        else {
            errorLabel.setText("Field missing, please correct.");
        }
       

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        daysComboBox.setItems(FXCollections.observableArrayList(1, 3, 5));
        typeOfTrainingComboBox.setItems(FXCollections.observableArrayList("Bodybuilding" , "Powerlifting"));
        
    }
    
}
