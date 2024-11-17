package com.example.fractalprototype;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Builder;

import java.util.Objects;

public class FractalViewBuilder implements Builder<Region> {
    private final FractalModel model;

    public FractalViewBuilder(FractalModel model) {
        this.model = model;
    }

    @Override
    public Region build() {
        BorderPane results = new BorderPane();
//        results.getStylesheets().add(
//                Objects.requireNonNull(this.getClass().getResource(
//                        "/style.css")).toExternalForm());
        results.setTop(new Label("top?"));
        results.setCenter(createCenter());
        results.setBottom(createButton());
        return results;
    }

    private Node createCenter() {
        VBox results = new VBox(6, usernameBox());
        results.setPadding(new Insets(20));
        return results;
    }

    private Node usernameBox() {
        return new HBox(6, new Label("Username:"), boundTextField(model.usernameProperty()));
    }

    private Node createButton() {
        Button clickMeButton = new Button("Click me!");
        HBox results = new HBox(10, clickMeButton);
        //clickMeButton.setOnAction(evt -> ...);
        results.setAlignment(Pos.CENTER_RIGHT);
        return results;
    }

    private Node boundTextField(StringProperty boundProperty) {
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(boundProperty);
        return textField;
    }
}
