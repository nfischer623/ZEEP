package com.example.fractalprototype;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class FractalController {
    private Builder<Region> viewBuilder;
    private FractalInteractor interactor;

    public FractalController() {
        FractalModel model = new FractalModel();
        interactor = new FractalInteractor(model);
//        viewBuilder = new FractalViewBuilder(model, interactor::saveBrushSize);
        viewBuilder = new FractalViewBuilder(model, interactor);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}