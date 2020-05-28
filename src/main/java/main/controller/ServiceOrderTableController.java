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
import main.model.*;

import java.io.IOException;
import java.util.Date;

public class ServiceOrderTableController {

    private static String returnID;
    private static String returnRole;

    @FXML
    private Button btnLayananKeluar;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private Button btnPemesanan;

    @FXML
    private TableView<Service> tableAll;

    @FXML
    private TableColumn<Service, Integer> serviceId;

    @FXML
    private TableColumn<Service, String> serviceName;

    @FXML
    private TableColumn<Service, String> serviceType;

    @FXML
    private TableColumn<Service, String> serviceSize;

    @FXML
    private TableColumn<Service, Double> servicePrice;

    @FXML
    private TableView<ServiceTransactionDetail> tableOrder;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> orderId;

    @FXML
    private TableColumn<ServiceTransactionDetail, String>  orderEmployee;

    @FXML
    private TableColumn<ServiceTransactionDetail, Date> orderDate;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> orderPet;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> orderService;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> orderFinished;

    @FXML
    private TableColumn<ServiceTransactionDetail, String> orderPaid;

    @FXML
    private TableColumn<ServiceTransactionDetail, Double> orderTotal;

    @FXML
    private ImageView addLogo;

    @FXML
    private Label addLabel;

    @FXML
    private ImageView editLogo;

    @FXML
    private Label editLabel;

    @FXML
    private ImageView deleteLogo;

    @FXML
    private Label deleteLabel;

    @FXML
    private TextField txtNama;

    @FXML
    private ComboBox<Pet> comboPet;

    @FXML
    private ComboBox<Service> comboService;

    @FXML
    private TextField txtCari;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnLihat;

    @FXML
    private Button btnBersih;

    @FXML
    private Button btnPesan;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnHapus;


    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    @FXML
    void handleButtonService(MouseEvent me) {
        if (me.getSource() == btnLayananKeluar) {
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/MainMenuSecondary.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        if (me.getSource() == btnPemesanan) {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/OrderServiceView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    void clearFields(MouseEvent event) {

    }

    @FXML
    void deleteOrder(MouseEvent event) {

    }

    @FXML
    void orderService(MouseEvent event) {

    }

    @FXML
    void searchService(ActionEvent event) {

    }

    @FXML
    void searchServices(ActionEvent event) {

    }

    @FXML
    void selectedRow(MouseEvent event) {

    }

    @FXML
    void switchOperations(MouseEvent event) {

    }

    @FXML
    void updateOrder(MouseEvent event) {

    }

}
