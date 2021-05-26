package edu.agile.controller;

import edu.agile.model.Game;
import edu.agile.service.GameService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    TextField gameName;
    @FXML
    TextArea gameDescription;
    @FXML
    public Button addGame;
    @FXML
    public Button abort;

    private final GameService gameService;

    public GameController() {
        this.gameService = GameService.getInstance();
    }

    /**
     * Adds new game to database and returns to main scene
     *
     * @param actionEvent
     */
    public void addGame(ActionEvent actionEvent) {
        gameService.addGame(getGame());
        setMainScene();
    }

    /**
     * Returns to main scene without adding new game
     *
     * @param actionEvent
     */
    public void abort(ActionEvent actionEvent) {
        setMainScene();
    }

    /**
     * Collects fields and turns them into a game object
     *
     * @return game object
     */
    private Game getGame() {
        return Game.builder()
                .name(gameName.getText())
                .description(gameDescription.getText())
                .build();
    }

    /**
     * Return to main scene
     */
    private void setMainScene() {
        try {
            Stage stage = (Stage) gameName.getScene().getWindow();

            stage.close();

            Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
            stage.setTitle("Spelsamling");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
