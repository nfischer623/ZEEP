package com.example.fractalprototype;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.canvas.*;

public class FractalApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }


//    private Region juliaSceneRoot;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new FractalController(primaryStage).getView());
        scene.getStylesheets().add("zeep-style.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("ZEEP v0.4");
        primaryStage.show();

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.1;
        double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.4;
        primaryStage.setX(x);
        primaryStage.setY(y);
    }
}