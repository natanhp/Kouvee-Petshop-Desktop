package main.controller;


import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.dao.*;
import main.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class PaymentServiceController implements Initializable {

    private static String returnID;
    private static String returnRole;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private Button btnPembayaranProduk;

    @FXML
    private Button btnPembayaranKeluar;

    @FXML
    private TableView<ServiceTransactionDetail> tableAll;

    @FXML
    private TableColumn<ServiceTransactionDetail, Integer> stdId;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> stId;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> stdService;

    @FXML
    private TableColumn<ServiceTransactionDetail, Date> stdDate;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> stdEmp;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> isFinished;

    @FXML
    private TableColumn<ServiceTransactionDetail, Double> stdPrice;

    @FXML
    private TextField txtCari;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnLihat;

    @FXML
    private Button btnBersih;

    @FXML
    private Button btnBayar;

    @FXML
    private TextField txtNominal;

    @FXML
    private TextField txtDisc;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtIdTransaksi;

    @FXML
    private TextField txtIdDetail;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    @FXML
    void clearFields(ActionEvent event) {
        txtCari.clear();
        txtIdDetail.clear();
        txtIdTransaksi.clear();
        txtNominal.clear();
        txtDisc.clear();
        txtTotal.clear();

        loadAllData();
    }

    @FXML
    void handleButtonPayment(MouseEvent me) {
        if (me.getSource() == btnPembayaranKeluar) {
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

        if (me.getSource() == btnMenuUtama) {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/MainMenuTertiary.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        else if (me.getSource() == btnPembayaranProduk) {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PaymentProductView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stdId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        stId.setCellValueFactory(cellData -> cellData.getValue().serviceTransaction_IdProperty());
        stdService.setCellValueFactory(cellData -> cellData.getValue().serviceIdProperty());
        stdPrice.setCellValueFactory(cellData -> cellData.getValue().subTotalProperty().asObject());
        stdDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        stdEmp.setCellValueFactory(cellData -> cellData.getValue().employees_IdProperty());
        isFinished.setCellValueFactory(cellData -> {
            StringProperty finished = cellData.getValue().getIsFinished();
            String string;
            if(finished.getValue().matches("1"))
            {
                string = "Yes";
            }
            else
            {
                string = "No";
            }

            return new ReadOnlyStringWrapper(string);
        });

        loadAllData();
    }

    private void loadAllData() {
        //Get all Payment information
        ObservableList<ServiceTransactionDetail> stdData = null;
        try {
            stdData = ServiceTransactionDetailDAO.searchTransactions();
            //Populate ServiceTransactionDetails on TableView
            populateServiceTransactionDetails(stdData);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    private void populateServiceTransactionDetails(ObservableList<ServiceTransactionDetail> stdData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(stdData);
    }

    @FXML
    void payService(ActionEvent event) {
        double total = Double.parseDouble(txtTotal.getText());
        double nominal = Double.parseDouble(txtNominal.getText());
        double disc = Double.parseDouble(txtDisc.getText());
        Alert info = new Alert(Alert.AlertType.CONFIRMATION);
        info.setX(550);
        info.setY(300);
        info.setHeaderText("");
        info.setContentText("Anda yakin untuk melakukan pembayaran ? " +
                "Pembayaran tidak dapat dibatalkan setelah Anda mengkonfirmasi pembayaran");
        info.showAndWait().ifPresent((btnType) -> {
            if (btnType == ButtonType.OK) {
                try {
                    double newTotal = total - disc;
                    double kembalian = newTotal - nominal;
                    DialogShowInfo("TOTAL : Rp. "+ newTotal + " KEMBALIAN : Rp. " + kembalian);
                    ServiceTransactionDAO.updatePayment(returnID, txtIdTransaksi.getText(), newTotal, 1);
                } catch (SQLException e) {
                    e.printStackTrace();
                    DialogShowInfo("An error occured while Updating payment");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    DialogShowInfo("An error occured. No class found for ServiceTransactionDAO");
                }
            }
        });

    }

    @FXML
    void searchServiceTransactionDetail(ActionEvent event) {

    }

    @FXML
    void searchServiceTransactionDetails(ActionEvent event) {

    }

    @FXML
    void selectedRow(MouseEvent me) throws SQLException, ClassNotFoundException {
        if (me.getClickCount() > 1) {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() throws SQLException, ClassNotFoundException {
        if (tableAll.getSelectionModel().getSelectedItem() != null) {
            ServiceTransactionDetail std = tableAll.getSelectionModel().getSelectedItem();

            txtIdDetail.setText(Integer.toString(std.getId()));
            txtIdTransaksi.setText(std.getServiceTransaction_Id());
            txtTotal.setText(Double.toString(std.getTotal()));
        }
    }

    private void DialogShowInfo(String text) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setX(550);
        info.setY(300);
        info.setHeaderText("");
        info.setContentText(text);
        info.showAndWait();
    }
}

