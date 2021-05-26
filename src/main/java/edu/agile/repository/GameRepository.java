package edu.agile.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.agile.model.Game;
import edu.agile.service.DatabaseService;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Handles CRUD for Game Collection in local MongoDB
 */
public class GameRepository {
    private static final String COLLECTION_NAME = "games";
    private static GameRepository instance;

    private final MongoCollection<Document> gameCollection;

    private GameRepository(String collectionName) {
        this.gameCollection = DatabaseService.getInstance().getCollection(collectionName);
    }

    public static GameRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new GameRepository(COLLECTION_NAME);
        }
        return instance;
    }

    /**
     * Add a game to the database
     *
     * @param document game to add
     */
    public void addGame(Document document) {
        gameCollection.insertOne(document);
    }

    /**
     * Find all games in the database
     *
     * @return list of documents
     */
    public List<Document> findAll() {
        List<Document> allGames = new ArrayList<>();
        try (MongoCursor<Document> cursor = gameCollection.find().iterator()) {
            cursor.forEachRemaining(allGames::add);
        }
        return allGames;
    }
}
