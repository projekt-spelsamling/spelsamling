package edu.agile.model;

import javafx.scene.image.Image;
import lombok.*;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    private String name;
    private String description;
    private Image image;
    private Image banner;
    private File game;

    @Override
    public String toString() {
        return getName();
    }
}
