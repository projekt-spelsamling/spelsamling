package edu.agile.service;

import edu.agile.model.Game;
import edu.agile.repository.GameRepository;
import org.bson.Document;

import java.util.List;
import java.util.Objects;

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
        gameRepository.addGame(game);
    }

    /**
     * Find all games in the database
     *
     * @return list of documents
     */
    public List<Document> findAll() {
        return gameRepository.findAll();
    }

//    private static Game toEntity(Document document) {
//
//        Game.getFields();
//        Game.builder()
//                .name(document.getString("name"))
//    }
}
