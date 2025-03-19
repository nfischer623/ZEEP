package com.example.fractalprototype;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Builder;
import processing.core.PApplet;

import java.io.File;
import java.util.Locale;

public class FractalViewBuilder implements Builder<Region> {
    private final FractalModel model;
    private final FractalSketch sketch;
    private final FileChooser fileChooser;
    private final Stage stage;


    public FractalViewBuilder(FractalModel model, Stage stage) {
        this.model = model;
        this.sketch = new FractalSketch(model, 875, 700);
        this.fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png"));
        this.stage = stage;
    }


    @Override
    public Region build() {
        BorderPane results = new BorderPane();
        results.setTop(new Label("Hello ITS Capstone!"));
        results.setCenter(createCenter());
        results.setBottom(createExitButton());
        MakeProcessingWindow();
        return results;
    }


    private Node createCenter() {
        HBox results = new HBox(6, createSettingsBar());
        results.setPadding(new Insets(50));
        return results;
    }


    private Node createSettingsBar() {
        VBox vbox = new VBox(6,
                createColorPicker(model.colorAProperty()),
                createColorPicker(model.colorBProperty()),
                createColorPicker(model.colorCProperty()),
                createColorPicker(model.colorDProperty()),
                createFractalDropdown(),
                createSaveImageButton());
        vbox.setAlignment(Pos.CENTER_LEFT);
        return vbox;
    }


    private Node createExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> exitApplication());
        HBox results = new HBox(10, exitButton);
        results.setAlignment(Pos.CENTER_RIGHT);
        return results;
    }


    private Node createColorPicker(ObjectProperty<Color> colorProperty) {
        ColorPicker colorPicker = new ColorPicker(colorProperty.get());
        colorPicker.setOnAction(e -> {
            colorProperty.set(colorPicker.getValue());
            sketch.updateColors(true);
        });
        return colorPicker;
    }


    private Node createFractalDropdown() {
        ComboBox<String> dropdown = new ComboBox(
                FXCollections.observableArrayList("Mandelbrot", "Julia"));
        dropdown.valueProperty().bindBidirectional(model.fractalTypeProperty());
        dropdown.setOnAction(e -> {
            sketch.setFractalType(dropdown.getValue().toLowerCase());
            sketch.redraw();
        });
        return dropdown;
    }


    private Node createSaveImageButton() {
        Button saveButton = new Button("Save as PNG");
        saveButton.setOnAction(e -> {
            fileChooser.setInitialFileName("myFractal");
            fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Image Files", "*.png"));
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                String fileName = file.getAbsolutePath();
                sketch.saveImage(fileName);
            }
        });
        return saveButton;
    }


    private void MakeProcessingWindow() {
        String[] processingArgs = {"processing_window"};
        PApplet.runSketch(processingArgs, this.sketch);
    }


    private void exitApplication() {
        sketch.exit();
        Platform.exit();
    }


    // no longer used
//    private Node createDrawButton() {
//        Button drawButton = new Button("Draw!");
//        drawButton.setOnAction(e -> {
//            sketch.updateColors(true);
//        });
//        HBox results = new HBox(10, drawButton);
//        results.setAlignment(Pos.CENTER_RIGHT);
//        return results;
//    }


//    // no longer used
//    private Node createBrushSizeBox() {
//        HBox hbox = new HBox(6, new Label("Brush size:"), boundTextField(model.brushSizeProperty()));
//        hbox.setAlignment(Pos.CENTER_LEFT);
//        return hbox;
//    }


//    // no longer used
//    private Canvas createCanvas(Color color) {
//        Canvas canvas = new Canvas(600, 500);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        canvas.setOnMouseDragged(paintHandler(gc));
//        gc.setFill(color);
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        return canvas;
//    }


    // no longer used
//    private Node boundTextField(StringProperty boundProperty) {
//        TextField textField = new TextField();
//        textField.textProperty().bindBidirectional(boundProperty);
//        textField.setText("20");
//        return textField;
//    }


//    // no longer used
//    private EventHandler<MouseEvent> paintHandler(GraphicsContext gc) {
//        return new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent e) {
//                double size = Double.parseDouble(model.getBrushSize());
//                double x = e.getX() - size / 2;
//                double y = e.getY() - size / 2;
//                gc.setFill(model.getColorA());
//                gc.fillOval(x, y, size, size);
//            }
//        };
//    }
}
