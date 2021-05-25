package edu.agile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        //todo ta bort exempelkod
//        GameService gameService = GameService.getInstance();
//        Game game = new Game("Counterstrike", "Police vs terrorists");
//        Game game2 = new Game("Doom", "Kill demons");
//        gameService.addGame(game);
//        gameService.addGame(game2);
//
//        gameService.findAll().forEach(System.out::println);
        launch();
    }

}