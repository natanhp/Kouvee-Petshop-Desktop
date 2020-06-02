package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportEMonthController {
    @FXML
    private Button btnMenuUtama;

    @FXML
    private Button btnLaporanKeluar;

    @FXML
    private Button btnPendapatanT;

    @FXML
    private Button btnProdukT;

    @FXML
    private Button btnLayananT;

    @FXML
    private Button btnPendapatanB;

    @FXML
    private Button btnPengadaanT;

    @FXML
    private Button btnPengadaanB;

    @FXML
    private Button btnCetakLaporan;

    @FXML
    private ComboBox<?> comboMonth;

    @FXML
    private ComboBox<?> comboYear;


    @FXML
    void handleButtonReport(MouseEvent me) {
        Scene scene = null;

        if (me.getSource() == btnLaporanKeluar) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setX(550);
            alert.setY(300);
            alert.setTitle("Exit Kouvee PetShop");
            alert.setHeaderText("");
            alert.setContentText("Are you sure you want to exit Kouvee PetShop ?");
            alert.showAndWait().ifPresent((btnType) -> {
                if (btnType == ButtonType.OK) {
                    System.exit(0);
                }
            });
        }
        else if (me.getSource() != btnLaporanKeluar) {
        }
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {

                if(me.getSource() == btnMenuUtama)
                {

                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/MainMenu.fxml")));
                }
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void handleButtonShowReport(MouseEvent me) {
        if(me.getSource() == btnPendapatanT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportEYearView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnPendapatanB)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportEMonthView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnLayananT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportServiceSView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnProdukT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportProductSView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if(me.getSource() == btnPengadaanT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportPYearView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnPengadaanB)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportPMonthView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    @FXML
    void printReport(ActionEvent ae) {

    }
}
