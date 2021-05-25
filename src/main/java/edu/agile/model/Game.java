package edu.agile.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    private String name;
    private String description;

    public static Set<String> getFields() {
        return Set.of("name", "description");
    }
}
