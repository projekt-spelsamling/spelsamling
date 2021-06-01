package edu.agile.controller;

import edu.agile.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
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
    TextArea description;

    @FXML
    ImageView banner;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(game.getName());
        description.setText(game.getDescription());
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
}

