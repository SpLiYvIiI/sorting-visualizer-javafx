package racxa.controllers;

import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import racxa.services.AioService;
import racxa.services.AioServiceImpl;
import racxa.sortingalgorithms.Sort;
import racxa.sortingalgorithms.SortingFactory;
import racxa.utils.Constants;
import racxa.utils.Helper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class SortingController implements Initializable {

    Sort currSortAlgo;
    Rectangle[] sortArr;
    SequentialTransition st;
    boolean isActive = false;
    boolean isSorted = false;
    AioService aioService;
    @FXML
    private Pane sortingPane;
    @FXML
    private ChoiceBox<String> sortingAlgorithm;
    @FXML
    private Button startSortButton;
    @FXML
    private Button pauseSortingButton;
    @FXML
    private Button generateRandomButton;
    @FXML
    private Slider sortingSpeedSlider;
    @FXML
    private Slider arraySizeSlider;
    @FXML
    private Button logOutButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        st = new SequentialTransition();
        Constants.ARRAYSIZE = (int) arraySizeSlider.getValue();
        Constants.OFFSET = Constants.PANEWIDTH / Constants.ARRAYSIZE;
        Constants.SORTINGSPEED = 20/(int) sortingSpeedSlider.getValue();
        aioService = new AioServiceImpl();
        aioService.getAlgoTypes();
        sortArr = Helper.generateArr();
        sortingPane.getChildren().addAll(sortArr);
        sortingAlgorithm.setItems(FXCollections.observableArrayList(Constants.AVAILABLEALGOS.keySet()));
        sortingAlgorithm.setValue(sortingAlgorithm.getItems().get(0));
    }

    public void startSorting(ActionEvent actionEvent) {
        st = new SequentialTransition();
        startSortButton.setDisable(true);
        generateRandomButton.setDisable(true);
        arraySizeSlider.setDisable(true);
        sortingSpeedSlider.setDisable(true);
        pauseSortingButton.setDisable(false);
        currSortAlgo = SortingFactory.createSort(sortingAlgorithm.getValue());
        st.getChildren().addAll(currSortAlgo.sort(sortArr));
        st.play();
        isActive = true;
        st.setOnFinished(finish -> {
            generateRandomButton.setDisable(false);
            arraySizeSlider.setDisable(false);
            sortingSpeedSlider.setDisable(false);
            pauseSortingButton.setDisable(true);
            isActive = false;
            isSorted = true;
            aioService.registerSort(sortingAlgorithm.getValue());
            log.info("User sorted array using " + sortingAlgorithm.getValue() + " algorithm");
        });
    }


    public void generateRandom(ActionEvent actionEvent) {
        startSortButton.setDisable(false);
        isSorted = false;
        sortingPane.getChildren().clear();
        sortArr = Helper.generateArr();
        sortingPane.getChildren().addAll(sortArr);
    }

    public void pauseSorting(ActionEvent actionEvent) {
        if (isActive) {
            pauseSortingButton.setText("Unpause");
            st.pause();
            isActive = false;
        } else {
            pauseSortingButton.setText("Pause");
            st.play();
            isActive = true;
        }
    }

    public void initArraySize(MouseEvent mouseEvent) {
        Constants.ARRAYSIZE = (int) arraySizeSlider.getValue();
        Constants.OFFSET = Constants.PANEWIDTH / Constants.ARRAYSIZE;
        startSortButton.setDisable(false);
        isSorted = false;
        sortingPane.getChildren().clear();
        sortArr = Helper.generateArr();
        sortingPane.getChildren().addAll(sortArr);
    }

    public void initSortingSpeed(MouseEvent mouseEvent) {
        Constants.SORTINGSPEED = 20/(int) sortingSpeedSlider.getValue();
    }

    public void logOut(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Constants.CURRUSER = null;
            st.stop();
            st.getChildren().clear();
            Pane entrancePane = fxmlLoader.load(getClass().getResourceAsStream("/views/entrance.fxml"));
            Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            log.info("User logged out");
            primaryStage.hide();
            primaryStage.setScene(new Scene(entrancePane));
            primaryStage.centerOnScreen();
            primaryStage.setTitle("Entrance");
            primaryStage.getIcons().clear();
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/static/entrancelogo.png")));
            primaryStage.show();
        }
        catch (IOException e) {
            log.error("Couldn't load view /views/entrance.fxml");
        }
    }

    public void openSortingHistory(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root = fxmlLoader.load(getClass().getResourceAsStream("/views/sorthistory.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("History");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/static/sorthistorylogo.jpg")));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
