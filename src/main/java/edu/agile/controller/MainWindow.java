package edu.agile.controller;

import edu.agile.model.Game;
import edu.agile.service.GameService;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    public GameService gameService;

    public ObservableList<Game> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameService = GameService.getInstance();


    }

    private void populateGameList() {
        //todo make repository return list of games (using mapper)
        //list = gameService.findAll();
    }
}
