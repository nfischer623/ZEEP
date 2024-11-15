package com.example.fxdemo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
    public static void main(String[] args) {
        launch(args);
    }

    private final StringProperty greeting = new SimpleStringProperty("");
    private final StringProperty name = new SimpleStringProperty("");

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent(),400,200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Region createContent() {
        TextField inputTextField = new TextField("");
        HBox inputRow = new HBox(new Label("Name:"), inputTextField);
        inputRow.setSpacing(6);
        inputRow.setAlignment(Pos.CENTER);

        Label outputLabel = new Label("");
        Button actionButton = new Button("Hello");
        actionButton.setOnAction(evt -> outputLabel.setText("Hello " + inputTextField.getText()));

        VBox results = new VBox(20, inputRow, outputLabel, actionButton);
        results.setAlignment(Pos.CENTER);

        return results;
    }

    private Node createGreetingButton() {
        Button results = new Button("Hello");
        results.setOnAction(evt -> setGreeting());
        return results;
    }

    private Node createInputRow() {
        TextField textField = new TextField("");
        textField.textProperty().bindBidirectional(name);
        HBox hBox = new HBox(6, new Label("Name:"), textField);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private Node createOutputLabel() {
        Label results = new Label("");
        results.textProperty().bind(greeting);
        return results;
    }

    private void setGreeting() {
        greeting.set("Hello " + name.get());
    }
}