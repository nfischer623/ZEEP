package com.example.fractalprototype;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FractalModel {
    private final StringProperty brushSize = new SimpleStringProperty("");

    public String getBrushSize() {
        return brushSize.get();
    }

    public StringProperty brushSizeProperty() {
        return brushSize;
    }

    public void setBrushSize(String brushSize) {
        this.brushSize.set(brushSize);
    }
}
