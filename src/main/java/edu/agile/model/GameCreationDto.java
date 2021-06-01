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
    private String description;
    private File file;
}
