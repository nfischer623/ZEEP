package com.example.fractalprototype;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
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

public class FractalViewBuilder implements Builder<Region> {
    private final FractalModel model;
    private final FractalSketch sketch;
    private final FileChooser fileChooser;
    private final Stage stage;

    private final FileChooser.ExtensionFilter imageExtFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
    private final FileChooser.ExtensionFilter zeepExtFilter = new FileChooser.ExtensionFilter("ZEEP Project Files", "*.zeep");


    public FractalViewBuilder(FractalModel model, Stage stage) {
        this.model = model;
        this.sketch = new FractalSketch(model, 875, 700);
        this.fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(imageExtFilter, zeepExtFilter);
        this.stage = stage;
    }


    @Override
    public Region build() {
        BorderPane results = new BorderPane();
        results.setTop(createMenuBar());
        results.setCenter(createCenter());
//        results.setBottom(createExitButton());
        results.setBottom(createBottom());
        MakeProcessingWindow();
        return results;
    }


    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(createFileMenu());

        return menuBar;
    }


    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(
                createSaveImageMenuItem(),
                createSaveZeepMenuItem(),
                createLoadZeepMenuItem());
        return fileMenu;
    }


    private Node createCenter() {
        HBox results = new HBox(6, createInteractables());
        results.setPadding(new Insets(25));
        return results;
    }


    private Node createInteractables() {
        VBox vbox = new VBox(6,
                createColorPicker(model.colorAProperty()),
                createColorPicker(model.colorBProperty()),
                createColorPicker(model.colorCProperty()),
                createColorPicker(model.colorDProperty()),
                createFractalDropdown(),
//                createSaveImageMenuItem(),
//                createSaveZeepMenuItem(),
//                createLoadZeepMenuItem(),
                createRecenterButton());
        vbox.setAlignment(Pos.CENTER_LEFT);
        return vbox;
    }


    private Node createBottom() {
        Label controls = new Label(
                "Click + drag to move fractal\n" +
                "+/- key to zoom in/out\n" +
                "'F' to enable Funky Mode\n" +
                "Right-click to pause (Julia only)");
        HBox results = new HBox(6, controls, createExitButton());
        results.setPadding(new Insets(50));
        return results;
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
        colorPicker.valueProperty().bindBidirectional(colorProperty);
        colorPicker.setOnAction(e -> {
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


    private MenuItem createSaveImageMenuItem() {
        MenuItem saveButton = new MenuItem("Export as PNG");
        saveButton.setOnAction(e -> {
            fileChooser.setInitialFileName("myFractal");
            fileChooser.setSelectedExtensionFilter(imageExtFilter);
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                String fileName = file.getAbsolutePath();
                sketch.saveImage(fileName);
            }
        });
        return saveButton;
    }


    private MenuItem createSaveZeepMenuItem() {
        MenuItem saveButton = new MenuItem("Export as ZEEP project");
        saveButton.setOnAction(e -> {
            fileChooser.setInitialFileName("myFractal");
            fileChooser.setSelectedExtensionFilter(zeepExtFilter);
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                String fileName = file.getAbsolutePath();
                sketch.saveZeep(fileName);
            }
        });
        return saveButton;
    }


    private MenuItem createLoadZeepMenuItem() {
        MenuItem loadButton = new MenuItem("Load from existing ZEEP project");
        loadButton.setOnAction(e -> {
            fileChooser.setSelectedExtensionFilter(zeepExtFilter);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                String fileName = file.getAbsolutePath();
                sketch.loadZeep(fileName);
            }
        });
        return loadButton;
    }


    private Node createRecenterButton() {
        Button recenterButton = new Button("Re-center");
        recenterButton.setOnAction(e -> {
            sketch.resetView();
        });
        return recenterButton;
    }


    private void MakeProcessingWindow() {
        String[] processingArgs = {"processing_window"};
        PApplet.runSketch(processingArgs, this.sketch);
    }


    private void exitApplication() {
        sketch.exit();
        Platform.exit();
    }
}
