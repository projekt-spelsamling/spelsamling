module edu.agile {
    requires lombok;
    requires mongo.java.driver;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    exports edu.agile;
    exports edu.agile.controller;
}
