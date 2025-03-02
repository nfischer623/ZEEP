package com.example.fractalprototype;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class FractalController {
    private final FractalViewBuilder viewBuilder;
    private FractalModel model = new FractalModel();

    public FractalController() {
        viewBuilder = new FractalViewBuilder(model);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}