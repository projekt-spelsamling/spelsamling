package edu.agile.controller;

import edu.agile.model.Game;
import edu.agile.service.GameService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    public GameService gameService;

    public ObservableList<Game> gameList;

    @FXML
    public Button infoButton;

    @FXML
    public ComboBox<Game> gameComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameService = GameService.getInstance();
        gameComboBox.setItems(populateGameList());
    }

    /**
     * Populates ObservableList gameList and returns itself
     *
     * @return ObservableList with games that was fetched from database
     */
    private ObservableList<Game> populateGameList() {
        gameList = FXCollections.observableArrayList(gameService.findAll());
        return gameList;
    }

    /**
     * Set stage for window with info about games
     *
     * @param actionEvent
     * @throws IOException
     */
    public void openInfo(ActionEvent actionEvent) throws IOException {
        //Get selected game
        Game selectedGame = gameComboBox.getSelectionModel().getSelectedItem();

        //Get stage
        Stage stage = (Stage) gameComboBox.getScene().getWindow();
        stage.close();

        //Set new controller and pass game
        FXMLLoader loader = new FXMLLoader((getClass().getResource("/info.fxml")));
        InfoController infoController = new InfoController(selectedGame);
        loader.setController(infoController);

        //Set stage with new scene
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
