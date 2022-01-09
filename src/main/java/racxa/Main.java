package racxa;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root;
        try(InputStream inputStream = getClass().getResourceAsStream("/views/entrance.fxml")){
            root = fxmlLoader.load(inputStream);
        }
        Scene scene=new Scene(root);
        primaryStage.setTitle("Entrance");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/static/entrancelogo.png")));
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
