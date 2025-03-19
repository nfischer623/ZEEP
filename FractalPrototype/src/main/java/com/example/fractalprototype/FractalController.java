package com.example.fractalprototype;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class FractalController {
    private final FractalViewBuilder fractalView;
    private final FractalModel model = new FractalModel();

    public FractalController() {
        fractalView = new FractalViewBuilder(model);
//        model.fractalTypeProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue,
//                                String oldValue, String newValue) {
//                getView();
//            }
//        });
    }

    public Region getView() {
        return fractalView.build();
    }
}