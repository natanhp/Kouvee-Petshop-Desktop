package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.model.Employee;

import java.io.IOException;


public class MainMenuController {

    @FXML
    private Button btnKelUkrHewan;

    @FXML
    private Button btnMainLogout;

    @FXML
    private Button btnKelSupplier;

    @FXML
    private Button btnKelTransaksi;

    @FXML
    private Button btnKelHewan;

    @FXML
    private Button btnKelLayanan;

    @FXML
    private Button btnKelPegawai;

    @FXML
    private Button btnKelProduk;

    @FXML
    private Button btnKelJnsHewan;

    @FXML
    private Button btnKelLaporan;

    @FXML
    private Button btnMainBantuan;

    @FXML
    private Button btnMainTentang;

    @FXML
    private Button btnKelCustomer;

    @FXML
    private Button btnMainKeluar;

    public void handleMainButtonAction(MouseEvent me) {
        if(me.getSource() == btnMainKeluar) {
            System.exit(0);
        }

        if(me.getSource() == btnMainLogout)
        {
            //logout
//            if (logoutAction().equals("Success")) {

            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/Main.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
//            }

        }

        if(me.getSource() == btnKelPegawai)
        {
            //logout
//            if (logoutAction().equals("Success")) {

            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/EmployeeView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
//            }

        }
    }

}