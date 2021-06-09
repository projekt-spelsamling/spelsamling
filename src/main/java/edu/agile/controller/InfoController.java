package edu.agile.controller;

import edu.agile.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoController implements Initializable {
    private final Game game;

    public InfoController(Game game) {
        this.game = game;
    }


    @FXML
    TextField name;

    @FXML
    TextField developer;

    @FXML
    TextField releaseDate;

    @FXML
    TextArea description;

    @FXML
    ImageView banner;

    @FXML
    MenuItem menuButton;

    @FXML
    MenuItem exitButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(game.getName());
        developer.setText(game.getDeveloper());
        description.setText(game.getDescription());
        releaseDate.setText(game.getReleaseDate());
        banner.setImage(getImage(game.getImageFile()));
    }

    private Image getImage(File file) {
        Image image = null;
        try {
            image = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }

    @FXML
    public void menuButtonAction(ActionEvent event) {
        if (event.getSource() == menuButton) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                Stage stage2 = (Stage) name.getScene().getWindow();
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
                Stage stage = (Stage) name.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

