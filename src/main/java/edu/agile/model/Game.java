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
    private String developerName;

    @Override
    public String toString() {
        return getName();
    }
}
