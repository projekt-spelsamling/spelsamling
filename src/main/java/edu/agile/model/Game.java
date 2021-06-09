package edu.agile.model;

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
    private File imageFile;
    private File game;
    private String developer;
    private String releaseDate;

    @Override
    public String toString() {
        return getName();
    }
}
