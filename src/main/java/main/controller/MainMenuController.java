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
import main.dao.EmployeeDAO;
import main.model.Employee;

import java.io.IOException;
import java.sql.SQLException;


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
        }

        if(me.getSource() == btnKelPegawai)
        {
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

        }

        if(me.getSource() == btnKelCustomer)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/CustomerView.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

        if(me.getSource() == btnKelUkrHewan)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PetSizeView.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

        if(me.getSource() == btnKelJnsHewan)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PetTypeView.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

        if(me.getSource() == btnKelSupplier)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/SupplierView.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

        if(me.getSource() == btnKelHewan)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PetView.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }


}
