package com.example.fractalprototype;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class FractalModel {
    private final StringProperty brushSize = new SimpleStringProperty("");
    private final ObjectProperty<Color> colorA = new SimpleObjectProperty<>(Color.WHITE);

    public String getBrushSize() {
        return brushSize.get();
    }

    public Color getColorA() {
        return colorA.get();
    }

    public StringProperty brushSizeProperty() {
        return brushSize;
    }

    public ObjectProperty<Color> colorAProperty() {
        return colorA;
    }

    public void setBrushSize(String brushSize) {
        this.brushSize.set(brushSize);
    }

    public void setColorA(Color color) {
        this.colorA.set(color);
    }
}
