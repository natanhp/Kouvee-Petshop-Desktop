package com.P3LJ2.P3L_J_2.controller;

import com.P3LJ2.P3L_J_2.ApplicationService;
import com.P3LJ2.P3L_J_2.MyFxApplication;
import com.P3LJ2.P3L_J_2.P3LJ2Application;
import com.P3LJ2.P3L_J_2.repository.EmployeeJpaRepository;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Component
@FxmlView("MyFxApplication.fxml")

@Controller
public class MyController {
    @FXML
    private Label showLabel;
    private ApplicationService applicationService;

    @Autowired
    public MyController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

//    EmployeeJpaRepository repository = new EmployeeJpaRepository();
//
//    String s = repository.finById(1).toString();
//    String s2 = String.valueOf(repository);
//    String s3 = "Hello !";

    public void loadBussinesLogic(ActionEvent actionEvent) {
        this.showLabel.setText(applicationService.showBussinesLogic());
    }
}
