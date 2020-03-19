package main.controller;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainPanelController {

    //Exit the program
    public void buttonExit(ActionEvent ae) {
        System.exit(0);
    }

    public void buttonLogout(ActionEvent ae) {
//        System.exit(0);
    }

    public void buttonEmployee(ActionEvent ae) {

    }

    public void buttonAbout(ActionEvent ae) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Kouvee Pet Shop");
        alert.setHeaderText("Pure Java Desktop Application ~ Pemenuhan Tugas P3L");
        alert.setContentText("Anda dapat menambah, melihat, mencari, menghapus dan memperbaharui data dengan program ini");
        alert.show();
    }
}
