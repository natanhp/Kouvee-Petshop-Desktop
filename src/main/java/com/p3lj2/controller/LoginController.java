package com.p3lj2.controller;

import com.p3lj2.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    public void login() throws IOException {
        MainApp.setRoot("Product");
    }
}
