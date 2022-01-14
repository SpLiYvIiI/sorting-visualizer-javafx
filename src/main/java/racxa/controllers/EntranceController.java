package racxa.controllers;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;
import racxa.services.AioService;
import racxa.services.AioServiceImpl;
import racxa.utils.Constants;
import racxa.utils.exceptions.InvalidCredentialsException;
import racxa.utils.exceptions.RecordAlreadyExistsException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

@Slf4j
public class EntranceController implements Initializable {
    private Properties p;
    private FadeTransition fadeOut;
    AioService aioService;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            FileReader reader=new FileReader("src/main/resources/properties/database.properties");
            p =new Properties();
            p.load(reader);
            Constants.jdbcUserName = p.getProperty("jdbcUserName");
            Constants.jdbcPassword = p.getProperty("jdbcPassword");
            Constants.jdbcConnectionUrl = p.getProperty("jdbcConnectionUrl");
        } catch (IOException e) {
            log.error("Can't read database options from src/main/resources/properties/database.properties");
        }
        fadeOut = new FadeTransition(Duration.millis(3500));
        aioService = new AioServiceImpl();
        fadeOut.setNode(errorMessage);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setCycleCount(1);
        fadeOut.setAutoReverse(false);
        errorMessage.setVisible(false);
    }

    public void signIn(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        String userName = usernameField.getText();
        String password = passwordField.getText();
        try{
            passwordField.setText("");
            aioService.loginUser(userName,password);
            usernameField.setText("");
            Pane sortingPane = fxmlLoader.load(getClass().getResourceAsStream("/views/sorting.fxml"));
            Stage primaryStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            log.info("User with " + Constants.CURRUSER + " id logged in");
            primaryStage.hide();
            primaryStage.setScene(new Scene(sortingPane));
            primaryStage.centerOnScreen();
            primaryStage.setTitle("Sorting visualizer");
            primaryStage.getIcons().clear();
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/static/mainlogo.png")));
            primaryStage.show();
        }
        catch (InvalidCredentialsException err){
            setNotification(err.getMessage(),Color.RED);
        }
        catch (IOException err){
            log.error("Couldn't load view /views/sorting.fxml");
        }
    }



    public void signUp(ActionEvent actionEvent) {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        try{
            aioService.registerUser(userName,password);
            setNotification("User successfully registered",Color.GREEN);
        }
        catch (InvalidCredentialsException | RecordAlreadyExistsException err){
            setNotification(err.getMessage(),Color.RED);
        }
        usernameField.setText("");
        passwordField.setText("");
    }
    private void setNotification(String message,Color c) {
        if (!errorMessage.isVisible()) {
            errorMessage.setVisible(true);
            errorMessage.setText(message);
            errorMessage.setTextFill(c);
            fadeOut.playFromStart();
            fadeOut.setOnFinished(finish -> {
                errorMessage.setVisible(false);
            });
        }
    }
}
