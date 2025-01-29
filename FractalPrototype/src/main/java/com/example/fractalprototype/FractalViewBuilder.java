package com.example.fractalprototype;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Builder;
import processing.core.PApplet;

public class FractalViewBuilder implements Builder<Region> {
    private final FractalModel model;

    public FractalViewBuilder(FractalModel model) {
        this.model = model;
    }

    @Override
    public Region build() {
        BorderPane results = new BorderPane();
        results.setTop(new Label("Hello ITS Capstone!"));
        results.setCenter(createCenter());
        results.setBottom(createExitButton());
        return results;
    }

    private Node createCenter() {
        HBox results = new HBox(6, createSettingsBar(), createCanvas(Color.BLUE));
        results.setPadding(new Insets(20));
        return results;
    }

    private Node createSettingsBar() {
        VBox vbox = new VBox(6, createBrushSizeBox(), createApplyButton());
        vbox.setAlignment(Pos.CENTER_LEFT);
        return vbox;
    }

    private Node createBrushSizeBox() {
        HBox hbox = new HBox(6, new Label("Brush size:"), boundTextField(model.brushSizeProperty()));
        hbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }

    private Node createApplyButton() {
        Button applyButton = new Button("Apply");
//        applyButton.setOnAction(this.interactor::saveBrushSize);
        HBox results = new HBox(10, applyButton);
        results.setAlignment(Pos.CENTER_RIGHT);
        return results;
    }

    private Canvas createCanvas(Color color) {
        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(paintHandler(gc));
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        MakeProcessingWindow(); // SHOULD NOT BE CALLED FROM HERE
        return canvas;
    }

    // this doesn't follow MVC/MVCI - needs to be refactored
    private EventHandler<MouseEvent> paintHandler(GraphicsContext gc) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                double size = Double.parseDouble(model.getBrushSize());
                double x = e.getX() - size / 2;
                double y = e.getY() - size / 2;
                gc.setFill(Color.WHITE);
                gc.fillOval(x, y, size, size);
            }
        };
    }

    private Node createExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(evt -> Platform.exit());
        HBox results = new HBox(10, exitButton);
        results.setAlignment(Pos.CENTER_RIGHT);
        return results;
    }

    private Node boundTextField(StringProperty boundProperty) {
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(boundProperty);
        textField.setText("20");
        return textField;
    }

    private void MakeProcessingWindow() {
        String[] processingArgs = {"processing_window"};
        ProcessingSketch processingSketch = new ProcessingSketch();
        PApplet.runSketch(processingArgs, processingSketch);
    }
}
