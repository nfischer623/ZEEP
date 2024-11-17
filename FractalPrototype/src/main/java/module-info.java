module com.example.fractalprototype {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.fractalprototype to javafx.fxml;
    exports com.example.fractalprototype;
}