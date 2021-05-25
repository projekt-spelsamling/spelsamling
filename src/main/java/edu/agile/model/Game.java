package edu.agile.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    private String name;
    private String description;

    @Override
    public String toString() {
        return getName();
    }
}
