package edu.agile.service;

import edu.agile.model.Game;
import edu.agile.model.GameCreationDto;
import edu.agile.repository.GameRepository;
import org.bson.Document;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameService {
    private static GameService instance;

    private GameRepository gameRepository;
    private ImageService imageService;

    private GameService() {
        this.gameRepository = GameRepository.getInstance();
        this.imageService = ImageService.getInstance();
    }

    public static GameService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new GameService();
        }
        return instance;
    }

    /**
     * Add a game to the database
     *
     * @param game game to add
     */
    public void addGame(GameCreationDto game) {
        String image = imageService.saveImageFile(game.getFile(), game.getName(), "banner");
        gameRepository.addGame(toDocument(game, image));
    }

    /**
     * Find all games in the database
     *
     * @return list of documents
     */
    public List<Game> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(GameService::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts document to game
     *
     * @param document
     * @return
     */
    private static Game toEntity(Document document) {
        return Game.builder()
                .name(document.getString("name"))
                .developer(document.getString("developer"))
                .description(document.getString("description"))
                .releaseDate(document.getString("release"))
                .imageFile(new File(document.getString("image")))
                .game(new File(document.getString("game")))
                .build();
    }

    private static Document toDocument(GameCreationDto game, String image) {
        Document document = new Document();
        document.append("name", game.getName());
        document.append("developer", game.getDeveloper());
        document.append("description", game.getDescription());
        document.append("release", game.getReleaseDate());
        document.append("image", image);
        document.append("game", game.getGame().getAbsolutePath());
        return document;
    }
}
