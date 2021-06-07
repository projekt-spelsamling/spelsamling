package edu.agile.controller;

import edu.agile.Utils.Program;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button playGame;
    @FXML
    public Button infoGame;
    @FXML
    public Button addGame;
    @FXML
    public VBox gameView;

    public ObservableList<Game> gameList;
    private final GameService gameService;

    /**
     * Constructor loads the game service
     */
    public MainController() {
        this.gameService = GameService.getInstance();
    }

    /**
     * Set stage for window with info about games
     *
     * @param actionEvent
     * @throws IOException
     */
    public void setInfoScene(ActionEvent actionEvent) throws IOException {
        //Get selected game
        //Game selectedGame = gameComboBox.getSelectionModel().getSelectedItem();
        //setInfoScene(selectedGame);
    }

    public void playGameAction(ActionEvent actionEvent) {
        Game game = gameComboBox.getSelectionModel().getSelectedItem();
        Program.run(game);
    }

    /**
     * Set the scene where you can add a new game.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void setGameScene(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addGame.getScene().getWindow();

        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/game.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Change scene to display more information about a game
     *
     * @param selectedGame the game to display more information about
     * @throws IOException
     */
    private void setInfoScene(Game selectedGame) throws IOException {
        //Get stage
        Stage stage = (Stage) gameView.getScene().getWindow();
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


    /**
     * Populates ObservableList gameList.
     * If the list is empty, the buttons that interact with games are disabled.
     * Otherwise, the first item in the list is selected
     */
    private void populateGameList() {
        gameList = FXCollections.observableList(gameService.findAll());

        if (gameList.isEmpty()) {
            disableGameButtons();
        } else {
            drawList(gameList);
        }
    }

    private void drawList(ObservableList<Game> gameList) {
        for (Game game : gameList) {
            Pane row = new Pane();

            Label name = makeLabel(game.getName());
            ImageView imageView = makeImage(game.getBanner());

            row.getChildren().add();
            row.getChildren().add();
            gameView.getChildren().add(row);
        }
    }

    /**
     *
     *
     * @param gameName
     * @return
     */
    private Label makeLabel(String gameName) {
        Label label = new Label(gameName);
        return label;
    }

    /**
     *
     *
     * @param banner
     * @return
     */
    private ImageView makeImage(Image banner) {
        ImageView imageView = new ImageView(banner);
        return imageView;
    }

    /**
     * Disables buttons which cannot be used when list of games is empty.
     * Reload the scene (with at least one game in the list) to enable the buttons again.
     */
    private void disableGameButtons() {
        playGame.setDisable(true);
        infoGame.setDisable(true);
    }

    /**
     * Initializes scene
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateGameList();
    }
}
