package main.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import main.dao.*;
import main.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ServiceOrderTableController implements Initializable {

    private static String returnID;
    private static String returnRole;

    @FXML
    private Button btnLayananKeluar;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private Button btnPemesanan;

    @FXML
    private TableView<ServiceDetail> tableAll;

    @FXML
    private TableColumn<ServiceDetail, Integer> serviceId;

    @FXML
    private TableColumn<ServiceDetail, String> serviceName;

    @FXML
    private TableColumn<ServiceDetail, String> serviceType;

    @FXML
    private TableColumn<ServiceDetail, String> serviceSize;

    @FXML
    private TableColumn<ServiceDetail, Double> servicePrice;

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
    private CheckBox checkBoxIsFinished;

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
    private TextField txtOrder;

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

    @FXML
    private Button btnTambah;


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

    //Populate ServiceDetails
    @FXML
    private void populateServiceDetail(ServiceDetail sd) {

        //Declare an ObservableList for TableView
        ObservableList<ServiceDetail> sdData = FXCollections.observableArrayList();
        //Add service detail to the ObservableList
        sdData.add(sd);
        //Set items to the tableAll
        tableAll.setItems(sdData);
    }


    @FXML
    private void populateServiceDetails(ObservableList<ServiceDetail> sdData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(sdData);
    }

    @FXML
    private void populateServiceTransactionDetails(ObservableList<ServiceTransactionDetail> stdData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableOrder.setItems(stdData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        serviceName.setCellValueFactory(cellData -> cellData.getValue().serviceIdProperty());
        serviceSize.setCellValueFactory(cellData -> cellData.getValue().petSizeIdProperty());
        serviceType.setCellValueFactory(cellData -> cellData.getValue().petTypeIdProperty());
        servicePrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        orderId.setCellValueFactory(cellData -> cellData.getValue().serviceTransaction_IdProperty());
        orderEmployee.setCellValueFactory(cellData -> cellData.getValue().employees_IdProperty());
        orderDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        orderPet.setCellValueFactory(cellData -> cellData.getValue().pets_IdProperty());
        orderService.setCellValueFactory(cellData -> cellData.getValue().serviceIdProperty());
        orderFinished.setCellValueFactory(cellData -> cellData.getValue().getIsFinished());
//        orderFinished.setCellValueFactory(cellData -> {
//            byte[] finished = cellData.getValue().getIsFinished();
//            Boolean boolean1 = Boolean.valueOf(finished);
//            boolean boolean2 = Boolean.parseBoolean(finished);
//            System.out.println(boolean1);
//            System.out.println(boolean2);
//
//            System.out.println(finished);
//            String string;
//            if(finished.equals(31))
//            {
//                string = "Yes";
//            }
//            else
//            {
//                string = "No";
//            }
//
//            return new ReadOnlyStringWrapper(string);
//        });

//        orderPaid.setCellValueFactory(cellData -> {
//            String paid = cellData.getValue().isIsPaid();
//            String string;
//            if(paid == "1")
//            {
//                string = "Yes";
//            }
//            else
//            {
//                string = "No";
//            }
//
//            return new ReadOnlyStringWrapper(string);
//        });

        orderTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        ObservableList typeList = FXCollections.observableArrayList();
        comboPet.getItems().clear();
        comboService.getItems().clear();
        comboPet.getItems().clear();
        comboService.setItems(typeList);
        comboPet.setItems(typeList);
        comboService.setItems(typeList);
        checkBoxIsFinished.setSelected(false);

        loadAllData();
    }

    private void loadAllData() {
        //Get all PetType information
        ObservableList<ServiceDetail> sdData = null;
        ObservableList<ServiceTransactionDetail> stdData = null;
        try {
            sdData = ServiceDetailDAO.searchServiceDetails();
            stdData = ServiceTransactionDetailDAO.searchServiceTransactionDetails();
            //Populate ServiceDetails on TableView
            populateServiceDetails(sdData);
            //Populate ServiceTransactionDetails on TableView
            populateServiceTransactionDetails(stdData);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void clearFields(ActionEvent ae) {

        txtOrder.clear();
        comboPet.getEditor().clear();
        comboService.getEditor().clear();
        txtCari.clear();

        loadAllData();
    }

    @FXML
    private void selectPet(MouseEvent me) throws SQLException, ClassNotFoundException {
        comboPet.setMaxHeight(20);

        try {
            //Try getting all the PetTypes information
            ObservableList<Pet> petData = PetDAO.searchPets();

            //Populate PetTypes on ComboBox
            populatePetComboBox(petData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all pettypes information from DB " + e);
            throw e;
        }
    }

    @FXML
    void selectService(MouseEvent me) throws SQLException, ClassNotFoundException {
        comboService.setMaxHeight(20);

        try {
            //Try getting all the PetTypes and PetSizes information
            ObservableList<Service> serviceData = ServiceDAO.searchServices();

            //Populate PetTypes and PetSizes on ComboBox
            populateServiceDetailComboBox(serviceData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all petsize information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void populatePetComboBox(ObservableList<Pet> petData) throws SQLException {

        //Set items to the comboBox
        if (comboPet == null) {
            return;
        }

        comboPet.setItems(petData);
        comboPet.setConverter(new StringConverter<Pet>() {

            @Override
            public String toString(Pet object) {
                if (object != null) {
                    return object.getName();
                }

                return "";
            }

            public int getObjectID(PetType object) {
                return object.getId();
            }

            @Override
            public Pet fromString(String string) {
                // TODO Auto-generated method stub
                return null;
            }
        });

        comboPet.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pet>() {
            @Override
            public void changed(ObservableValue<? extends Pet> observable, Pet oldValue, Pet newValue) {

            }
        });

    }

    @FXML
    private void populateServiceDetailComboBox(ObservableList<Service> serviceData) throws SQLException {

        //Set items to the comboBox
        if (comboService == null) {
            return;
        }

        comboService.setItems(serviceData);
        comboService.setConverter(new StringConverter<Service>() {

            @Override
            public String toString(Service object) {
                if (object != null) {
                    return object.getServiceName();
                }

                return "";
            }

            public int getObjectID(PetSize object) {
                return object.getId();
            }

            @Override
            public Service fromString(String string) {
                // TODO Auto-generated method stub
                return null;
            }
        });

        comboService.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Service>() {
            @Override
            public void changed(ObservableValue<? extends Service> observable, Service oldValue, Service newValue) {

            }
        });

    }

    @FXML
    void deleteOrder(ActionEvent ae) {

    }

    @FXML
    void orderService(MouseEvent event) {

    }

    @FXML
    void searchService(ActionEvent event) {

    }

    @FXML
    void searchServices(ActionEvent event) {
        loadAllData();
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

    @FXML
    void addServiceOrder(ActionEvent event) {

    }

}
