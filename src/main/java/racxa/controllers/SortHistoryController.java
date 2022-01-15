package racxa.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import racxa.dtos.SortHistoryDto;
import racxa.repositories.AioRepository;
import racxa.repositories.AioRepositoryImpl;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SortHistoryController implements Initializable {
    private AioRepository aioRepository;
    @FXML
    private TableView sortHistoryTable;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<SortHistoryDto, String> column1 = new TableColumn<>("User id");
        column1.setCellValueFactory(new PropertyValueFactory<>("userId"));
        column1.setPrefWidth(85.59997844696045);
        TableColumn<SortHistoryDto, String> column2 = new TableColumn<>("Username");
        column2.setCellValueFactory(new PropertyValueFactory<>("username"));
        column2.setPrefWidth(129.60003662109375);
        TableColumn<SortHistoryDto, String> column3 = new TableColumn<>("Algorithm Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("algoType"));
        column3.setPrefWidth(165.59996032714844);
        TableColumn<SortHistoryDto, String> column4 = new TableColumn<>("Sorting date");
        column4.setCellValueFactory(new PropertyValueFactory<>("sortDate"));
        column4.setPrefWidth(206.39996337890625);
        sortHistoryTable.getColumns().add(column1);
        sortHistoryTable.getColumns().add(column2);
        sortHistoryTable.getColumns().add(column3);
        sortHistoryTable.getColumns().add(column4);
        aioRepository = new AioRepositoryImpl();
        ArrayList<SortHistoryDto> sortHistoryDtos = aioRepository.getSortingHistory();
        for(SortHistoryDto dt : sortHistoryDtos)
            sortHistoryTable.getItems().add(dt);
    }
}
