package edu.agile.controller;

import edu.agile.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    private Game game;

    public InfoController(Game game) {
        this.game = game;
    }

    @FXML
    TextField gameName;

    @FXML
    TextArea gameDescription;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameName.setText(game.getName());
        gameDescription.setText(game.getDescription());
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }


}
