module paint {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens paint to javafx.fxml;
    exports paint;
}
