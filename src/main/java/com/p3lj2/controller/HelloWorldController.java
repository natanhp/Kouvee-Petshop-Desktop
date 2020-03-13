package com.p3lj2.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloWorldController {
    @FXML
    Label helloWorld;

    public void setHelloWorld(String helloWorld) {
        this.helloWorld.setText(helloWorld);
    }
}
