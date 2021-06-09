package edu.agile.model;

import lombok.*;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCreationDto {
    private String name;
    private String developer;
    private String description;
    private String releaseDate;
    private File file;
    private File game;
}
