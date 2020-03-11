package com.P3LJ2.P3L_J_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("MyFxApplication.fxml")

public class MyController {
    @FXML
    private Label showLabel;
    private WeatherService weatherService;

    @Autowired
    public MyController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void loadWeatherForecast(ActionEvent actionEvent) {
        String name = "Jack";
        this.showLabel.setText(weatherService.getWeatherForecast());
    }
}
