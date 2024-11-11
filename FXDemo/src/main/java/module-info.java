module com.example.fxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.fxdemo to javafx.fxml;
    exports com.example.fxdemo;
}