package edu.agile.service;

import edu.agile.model.Game;
import edu.agile.model.GameCreationDto;
import edu.agile.repository.GameRepository;
import javafx.scene.image.Image;
import org.bson.Document;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameService {
    private static GameService instance;

    private final GameRepository gameRepository;
    private final ImageService imageService;

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
        String image = imageService.saveImageFile(game.getImage(), game.getName(), "big");
        String banner = imageService.saveImageFile(game.getBanner(), game.getName(), "banner");
        gameRepository.addGame(toDocument(game, image, banner));
    }

    /**
     * Find all games in the database
     *
     * @return list of documents
     */
    public List<Game> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Converts document to game
     *
     * @param document
     * @return
     */
    private Game toEntity(Document document) {
        return Game.builder()
                .name(document.getString("name"))
                .description(document.getString("description"))
                .image(imageService.toImage(new File(document.getString("image"))))
                .banner(imageService.toImage(new File(document.getString("banner"))))
                .game(new File(document.getString("game")))
                .build();
    }

    private Document toDocument(GameCreationDto game, String image, String banner) {
        Document document = new Document();
        document.append("name", game.getName());
        document.append("description", game.getDescription());
        document.append("image", image);
        document.append("banner", banner);
        document.append("game", game.getGame().getAbsolutePath());
        return document;
    }
}
