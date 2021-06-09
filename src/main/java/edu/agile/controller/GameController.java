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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    public TextField bannerPath;
    @FXML
    public Button bannerChooseButton;
    @FXML
    public Button addGame;
    @FXML
    public Button abort;
    @FXML
    public MenuItem menuButton;
    @FXML
    public MenuItem exitButton;
    @FXML
    public TextField gamePath;
    @FXML
    public Button gameChooseButton;

    private File imageFile;
    private File gameFile;

    private File bannerFile;

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
        return GameCreationDto.builder()
                .name(gameName.getText())
                .description(gameDescription.getText())
                .image(imageFile)
                .game(gameFile)
                .banner(bannerFile)
                .build();
    }

    /**
     * Return to main scene
     */
    private void setMainScene() {
        try {
            Stage stage = (Stage) gameName.getScene().getWindow();

            stage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main.fxml")));
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
        imagePath.setText(imageFile.getAbsolutePath());
    }

    public void chooseBannerPath(ActionEvent actionEvent) {
        Stage stage = (Stage) imageChooseButton.getScene().getWindow();
        bannerFile = fileChooser.showOpenDialog(stage);
        bannerPath.setText(imageFile.getAbsolutePath());
    }

    public void chooseGamePath(ActionEvent actionEvent) {
        Stage stage = (Stage) gameChooseButton.getScene().getWindow();
        gameFile = fileChooser.showOpenDialog(stage);
        gamePath.setText(gameFile.getAbsolutePath());
    }

    @FXML
    public void menuButtonAction(ActionEvent event) {
        if (event.getSource() == menuButton) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                Stage stage2 = (Stage) gameName.getScene().getWindow();
                stage2.close();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void exitButtonAction(ActionEvent event) {
        if (event.getSource() == exitButton) {
            try {
                Stage stage = (Stage) gameName.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}
