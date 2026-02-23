module org.calculoimc {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.calculoimc to javafx.fxml, javafx.graphics;
    opens org.calculoimc.controller to javafx.fxml;
    opens org.calculoimc.model to javafx.base;
}