package com.example.fractalprototype;

import processing.core.*;

public abstract class FractalSketch extends PApplet {
    protected final FractalModel model;
    protected final int sketchWidth, sketchHeight;
    protected String fractalType;
    protected int[] colorPicks;


    public FractalSketch(FractalModel model, int width, int height) {
        this.model = model;
        this.sketchWidth = width;
        this.sketchHeight = height;
    }


    public abstract void updateColors(boolean isRedraw);
    public abstract void setup();
    public abstract void draw();
}
