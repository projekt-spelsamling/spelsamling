package edu.agile.service;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DatabaseService {
    private static final String HOST = "localhost";
    private static final String PORT = "27017";
    private static final String DB_NAME = "database-name";
    private static final DatabaseService INSTANCE = new DatabaseService();

    private final MongoDatabase database;

    private DatabaseService() {
        ConnectionString connectionString = new ConnectionString(String.format("mongodb://%s:%s", HOST, PORT));
        MongoClient client = MongoClients.create(connectionString);
        this.database = client.getDatabase(DB_NAME);
    }

    public static DatabaseService getInstance() {
        return INSTANCE;
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
}
