module org.onton {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires itextpdf;

    opens org.onton to javafx.fxml;
    exports org.onton.entity;
    exports org.onton;
}