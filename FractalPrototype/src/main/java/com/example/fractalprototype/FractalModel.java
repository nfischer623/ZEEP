package com.example.fractalprototype;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FractalModel {
    private final StringProperty username = new SimpleStringProperty("");

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
}
