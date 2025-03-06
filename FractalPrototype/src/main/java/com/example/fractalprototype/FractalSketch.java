package com.example.fractalprototype;

import processing.core.*;

public abstract class FractalSketch extends PApplet {
    protected final FractalModel model;
    protected final int sketchWidth, sketchHeight;
    int[] colorPicks = new int[4];

    public FractalSketch(FractalModel model, int width, int height) {
        this.model = model;
        this.sketchWidth = width;
        this.sketchHeight = height;
        updateColors(false);
    }

    public void updateColors(boolean isRedraw) {
        colorPicks[0] = model.getColorProcessingValue(model.getColorA());
        colorPicks[1] = model.getColorProcessingValue(model.getColorB());
        colorPicks[2] = model.getColorProcessingValue(model.getColorC());
        colorPicks[3] = model.getColorProcessingValue(model.getColorD());

        if (isRedraw) {
            redraw();
        }
    }

    public abstract void setup();
    public abstract void draw();
}
