package edu.agile.service;

import edu.agile.model.Game;
import edu.agile.repository.GameRepository;
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameService {
    private static GameService instance;

    private GameRepository gameRepository;

    private GameService() {
        this.gameRepository = GameRepository.getInstance();
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
    public void addGame(Game game) {
        gameRepository.addGame(toDocument(game));
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
                .description(document.getString("description"))
                .build();
    }

    private static Document toDocument(Game game) {
        Document document = new Document();
        document.append("name", game.getName());
        document.append("description", game.getDescription());
        return document;
    }
}
