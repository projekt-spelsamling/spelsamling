package edu.agile.controller;

import edu.agile.model.GameCreationDto;
import edu.agile.service.GameService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    TextField gameName;
    @FXML
    TextArea gameDescription;
    @FXML
    public TextField imagePath;
    @FXML
    public Button imageChooseButton;
    @FXML
    public Button addGame;
    @FXML
    public Button abort;

    private File imageFile;

    public FileChooser fileChooser = new FileChooser();

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
    private GameCreationDto getGame() {
        //todo kolla så alla obligatoriska fälten innehåller info
        return GameCreationDto.builder()
                .name(gameName.getText())
                .description(gameDescription.getText())
                .file(imageFile)
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

    public void chooseImagePath(ActionEvent actionEvent) {
        Stage stage = (Stage) imageChooseButton.getScene().getWindow();
        imageFile = fileChooser.showOpenDialog(stage);
        //todo handle when no file is choses (exit window)
        imagePath.setText(imageFile.getAbsolutePath());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
