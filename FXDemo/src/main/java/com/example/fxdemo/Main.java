package com.example.fxdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContents(),400,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Region createContents() {
        HBox results = new HBox(new Label("Name:"), new TextField(""));
        results.setSpacing(6);
        results.setPadding(new Insets(0,0,0,50));
        results.setAlignment(Pos.CENTER_LEFT);
        return results;
    }
}