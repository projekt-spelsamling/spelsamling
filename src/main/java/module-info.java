module edu.agile {
    requires lombok;
    requires mongo.java.driver;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.apache.commons.io;
    opens edu.agile.controller to javafx.fxml;
    exports edu.agile;
    exports edu.agile.controller;
    exports edu.agile.model;
}
