package com.example.fractalprototype;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

//    @Override
//    public void start(Stage primaryStage) {
//        Scene scene = new Scene(createContent(), 1000, 800);
//        primaryStage.setTitle("Fractal Capstone v0.1");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new FractalController().getView());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fractal Capstone v0.1");
        primaryStage.show();

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.1;
        double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.4;
        primaryStage.setX(x);
        primaryStage.setY(y);
    }

//    private Region createContent() {
//        HBox results = new HBox(20, createButton(), createCanvas(Color.BLUE));
//        results.setAlignment(Pos.CENTER);
//        return results;
//    }
//
//    private Button createButton() {
//        Button results = new Button("Click me!");
//        //results.setOnAction(evt -> ...);
//        return results;
//    }
//
//    private Canvas createCanvas(Color color) {
//        Canvas canvas = new Canvas(600, 500);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(color);
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        return canvas;
//    }
}