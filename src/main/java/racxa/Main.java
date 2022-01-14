package racxa;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Main extends Application{

    @Override
    public void start(Stage primaryStage)  {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root;
        try(InputStream inputStream = getClass().getResourceAsStream("/views/entrance.fxml")){
            root = fxmlLoader.load(inputStream);
            log.info("Application initialized");
            Scene scene=new Scene(root);
            primaryStage.setTitle("Entrance");
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/static/entrancelogo.png")));
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            log.error("Couldn't load view /views/entrance.fxml");
        }
    }

    public static void main(String[] args){
        launch(args);
    }

}
