module com.example.pokerprogram {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.pokerprogram to javafx.fxml;
    exports com.example.pokerprogram;
}