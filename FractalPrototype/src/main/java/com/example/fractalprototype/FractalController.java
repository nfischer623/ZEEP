package com.example.fractalprototype;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/*
* As-is, this class doesn't do much besides call the view builder.
*
* There is probably some functionality currently in FractalViewBuilder and
* FractalSketch that could be moved here instead - this would be an ideal
* thing to work on in a world where we have more time for this project.
* Unfortunately, it got pushed aside in favor of more important basic
* functionality. So, the app is Model-View-Controller, but admittedly not very
* good Model-View-Controller. The framework did serve as a good concepting
* tool, though, so it very much still helped to guide the philosophy of the
* code.
*/

public class FractalController {
    private final FractalViewBuilder fractalView;
    private final FractalModel model = new FractalModel();

    public FractalController(Stage stage) {
        fractalView = new FractalViewBuilder(model, stage);
    }

    public Region getView() {
        return fractalView.build();
    }
}