package com.example.fractalprototype;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.canvas.*;

public class FractalApplication extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }

//    @Override
//    public void start(Stage primaryStage) {
//        Scene scene = new Scene(createContent(), 1000, 800);
//        primaryStage.setTitle("Fractal Capstone v0.1");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new FractalController().getView()));
        primaryStage.show();
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