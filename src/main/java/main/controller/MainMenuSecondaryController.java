package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuSecondaryController implements Initializable {

    private static String returnName;
    @FXML
    private Label nameLogged;

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

    public static void getUnameLogin (String uname) {
        returnName = uname;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setWelcomeLabel();
    }

    private void setWelcomeLabel() {
        nameLogged.setTextFill(Color.WHITESMOKE);
        nameLogged.setText("Welcome, " + returnName);
        nameLogged.getText();
    }

    private void setImageButton() {

    }

    public void handleMainButtonAction(MouseEvent me) {
        if(me.getSource() == btnMainKeluar) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Kouvee PetShop");
            alert.setHeaderText("");
            alert.setContentText("Are you sure you want to exit Kouvee PetShop ?");
            alert.showAndWait().ifPresent((btnType) -> {
                if (btnType == ButtonType.OK) {
                    System.exit(0);
                }
            });
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

        if(me.getSource() == btnKelCustomer)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/CustomerSecondaryView.fxml")));
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PetSecondaryView.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }

        if(me.getSource() == btnKelProduk)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ProductView.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }


}
