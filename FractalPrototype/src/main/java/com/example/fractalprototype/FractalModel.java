package com.example.fractalprototype;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class FractalModel {
    private final ObjectProperty<Color> colorA = new SimpleObjectProperty<Color>(Color.rgb(0,102,153));
    private final ObjectProperty<Color> colorB = new SimpleObjectProperty<Color>(Color.rgb(174,130,250));
    private final ObjectProperty<Color> colorC = new SimpleObjectProperty<Color>(Color.rgb(242,97,177));
    private final ObjectProperty<Color> colorD = new SimpleObjectProperty<Color>(Color.rgb(255,180,51));
    private final StringProperty fractalType = new SimpleStringProperty("Mandelbrot");

    public Color getColorA() {
        return colorA.get();
    }

    public Color getColorB() { return colorB.get(); }

    public Color getColorC() { return colorC.get(); }

    public Color getColorD() { return colorD.get(); }

    public String getFractalType() { return fractalType.get(); }


    public int getColorProcessingValue(Color color) {
        int r = (int)(color.getRed() * 255);
        int g = (int)(color.getGreen() * 255);
        int b = (int)(color.getBlue() * 255);

        // bit-shifting based on the processing.core.PApplet color() function
        // why do they store their colors like this.
        return -16777216 | r << 16 | g << 8 | b;
    }


    public Color getColorFromString(String color) {
        int colorInt = Integer.parseInt(color);
        System.out.println(colorInt);
        return Color.web(String.format("#%06X", (0xFFFFFF & colorInt)));
    }


    public ObjectProperty<Color> colorAProperty() {
        return colorA;
    }

    public ObjectProperty<Color> colorBProperty() { return colorB; }

    public ObjectProperty<Color> colorCProperty() { return colorC; }

    public ObjectProperty<Color> colorDProperty() { return colorD; }

    public StringProperty fractalTypeProperty() { return fractalType; }


    public void setColorA(Color color) {
        this.colorA.set(color);
    }

    public void setColorB(Color color) {
        this.colorB.set(color);
    }

    public void setColorC(Color color) {
        this.colorC.set(color);
    }

    public void setColorD(Color color) {
        this.colorD.set(color);
    }

    public void setFractalType(String type) { this.fractalType.set(type); }
}
