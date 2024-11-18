package com.example.fractalprototype;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FractalInteractor {
    private FractalModel model;

    public FractalInteractor(FractalModel model) {
        this.model = model;
    }

    public void saveBrushSize(ActionEvent event) {
        System.out.println("Applying brush size: " + model.getBrushSize());
    }
}
