package main.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
    private ImageView addLogoService;

    @FXML
    private Label addLabel;

    @FXML
    private Label addLabelService;

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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ServiceOrderTableView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    private void switchOperations(MouseEvent me) {
        addLabel.setTextFill(Color.WHITE);
        if(me.getSource() == addLabel) {
            btnPesan.setDisable(false);
            btnPerbarui.setDisable(true);
            btnHapus.setDisable(true);
            btnTambah.setDisable(true);
            txtOrder.setDisable(false);
            comboPet.setDisable(false);
            comboService.setDisable(false);

            addLabel.setTextFill(Color.WHITE);
            addLabelService.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_white_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if(me.getSource() == addLabelService) {
            btnPesan.setDisable(true);
            btnPerbarui.setDisable(true);
            btnHapus.setDisable(true);
            btnTambah.setDisable(false);
            txtOrder.setDisable(false);
            comboPet.setDisable(true);
            comboService.setDisable(false);

            addLabel.setTextFill(Color.BLACK);
            addLabelService.setTextFill(Color.WHITE);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_white_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if(me.getSource() == editLabel) {
            btnPesan.setDisable(true);
            btnPerbarui.setDisable(false);
            btnHapus.setDisable(true);
            btnTambah.setDisable(true);
            txtOrder.setDisable(false);
            comboPet.setDisable(false);
            comboService.setDisable(false);

            addLabel.setTextFill(Color.BLACK);
            addLabelService.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.WHITE);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_white_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if(me.getSource() == deleteLabel) {
            btnPesan.setDisable(true);
            btnPerbarui.setDisable(true);
            btnHapus.setDisable(false);
            btnTambah.setDisable(true);
            txtOrder.setDisable(false);
            comboPet.setDisable(true);
            comboService.setDisable(true);

            addLabel.setTextFill(Color.BLACK);
            addLabelService.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.WHITE);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_white_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
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
        orderFinished.setCellValueFactory(cellData -> {
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

        orderTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        ObservableList typeList = FXCollections.observableArrayList();
        comboPet.getItems().clear();
        comboService.getItems().clear();
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

            //Populate Pets on ComboBox
            populatePetComboBox(petData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all pets information from DB " + e);
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
        try {
            ServiceTransactionDAO.deleteServiceTransactionWithId(txtOrder.getText());
            ServiceTransactionDetailDAO.deleteTranWithId(txtOrder.getText());

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem occurred while deleting Service Order");
        }
    }

    @FXML
    void orderService(ActionEvent event) {
        try {

            String peliharaan = Integer.toString(comboPet.getValue().getId());
            String layanan = Integer.toString(comboService.getValue().getId());

            PetSize size = PetDAO.searchSize(comboPet.getValue().getPetSize_name());
            PetType type = PetDAO.searchType(comboPet.getValue().getPetType_name());

            String petSize = Integer.toString(size.getId());
            String petType = Integer.toString(type.getId());

            int isFinished = checkBoxIsFinished.isSelected() ? 1 : 0;

            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);

            ServiceTransactionDAO.insertTransaction(returnID, txtOrder.getText(), date,
                    peliharaan, returnID, 0);
            ServiceDetail detailID = ServiceDetailDAO.searchServiceDetail(petSize, petType, layanan);
            String ServiceDetail_Id = Integer.toString(detailID.getId());

            ServiceTransactionDetailDAO.insertStd(returnID, isFinished, ServiceDetail_Id, txtOrder.getText());
            ServiceTransactionDAO.updateEntries(returnID, txtOrder.getText(), date, ServiceDetail_Id, returnID, peliharaan, 0);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem occurred while inserting pettype");
        }
    }

    @FXML
    void searchService(ActionEvent event) {

    }

    @FXML
    void searchServices(ActionEvent event) {
        loadAllData();
    }

    @FXML
    void selectedRowService(MouseEvent me) {
//        if (me.getClickCount() > 1) {
//            editWithSelectedRowService();
//        }
    }

    @FXML
    void selectedRowOrder(MouseEvent me) throws SQLException, ClassNotFoundException {
        if (me.getClickCount() > 1) {
            editWithSelectedRowOrder();
        }
    }

    @FXML
    void updateOrder(ActionEvent event) {
        String orderID = txtOrder.getText().trim();
        if (comboPet.getValue() == null || comboService.getValue() == null || orderID.equals("")) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }

        try {
            String peliharaan = Integer.toString(comboPet.getValue().getId());
            String layanan = Integer.toString(comboService.getValue().getId());

            PetSize size = PetDAO.searchSize(comboPet.getValue().getPetSize_name());
            PetType type = PetDAO.searchType(comboPet.getValue().getPetType_name());

            String petSize = Integer.toString(size.getId());
            String petType = Integer.toString(type.getId());

            int isFinished = checkBoxIsFinished.isSelected() ? 1 : 0;

            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);

            ServiceDetail detailID = ServiceDetailDAO.searchServiceDetail(petSize, petType, layanan);
            String ServiceDetail_Id = Integer.toString(detailID.getId());

            ServiceTransactionDetail std = ServiceTransactionDetailDAO.searchID(ServiceDetail_Id, txtOrder.getText());

            String stdID = Integer.toString(std.getId());

            if(isFinished == 1) {
                Alert info = new Alert(Alert.AlertType.CONFIRMATION);
                info.setX(550);
                info.setY(300);
                info.setHeaderText("");
                info.setContentText("Konfirmasi pesanan telah selesai ?");
                info.showAndWait().ifPresent((btnType) -> {
                    if (btnType == ButtonType.OK) {
                        try {
                            ServiceTransactionDetailDAO.updateEntries(returnID, stdID, isFinished, ServiceDetail_Id, txtOrder.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("An error occured updating transaction details");
                            DialogShowInfo("Pesanan gagal dikonfirmasi");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            ServiceTransactionDAO.updateEntries(returnID, txtOrder.getText(), date, ServiceDetail_Id, returnID, peliharaan, 0);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            System.out.println("An error occured updating transactions");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        DialogShowInfo("Pesanan telah selesai dikonfirmasi");
                    }
                });
            } else {
                ServiceTransactionDetailDAO.updateEntries(returnID, stdID, isFinished, ServiceDetail_Id, txtOrder.getText());
                ServiceTransactionDAO.updateEntries(returnID, txtOrder.getText(), date, ServiceDetail_Id, returnID, peliharaan, 0);
            }

            loadAllData();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem occurred while updating order");
        }
    }

    private void editWithSelectedRowOrder() throws SQLException, ClassNotFoundException {
        if (tableOrder.getSelectionModel().getSelectedItem() != null) {
            ServiceTransactionDetail std = tableOrder.getSelectionModel().getSelectedItem();

            ObservableList<Pet> petData = PetDAO.searchPets();
            populatePetComboBox(petData);
            ObservableList<Service> serviceData = ServiceDAO.searchServices();
            populateServiceDetailComboBox(serviceData);

            txtOrder.setText(std.getServiceTransaction_Id());
            comboPet.getItems();
            comboService.getItems();

            StringProperty finished = std.getIsFinished();
            boolean status;
            if(finished.getValue().matches("1"))
            {
                status = true;
            }
            else
            {
                status = false;
            }

            checkBoxIsFinished.setSelected(status);

            for (Pet pet : comboPet.getItems()) {
                if (pet.getName().equals(std.getPets_Id())) {
                    comboPet.getSelectionModel().select(pet);
                }
            }

            for (Service service : comboService.getItems()) {
                if (service.getServiceName().equals(std.getServiceId())) {
                    comboService.getSelectionModel().select(service);
                }
            }
        }
    }

    @FXML
    void addServiceOrder(ActionEvent event) {
        try {
            String layanan = Integer.toString(comboService.getValue().getId());

            PetSize size = PetDAO.searchSize(comboPet.getValue().getPetSize_name());
            PetType type = PetDAO.searchType(comboPet.getValue().getPetType_name());

            String petSize = Integer.toString(size.getId());
            String petType = Integer.toString(type.getId());

            int isFinished = checkBoxIsFinished.isSelected() ? 1 : 0;
            ServiceDetail detailID = ServiceDetailDAO.searchServiceDetail(petSize, petType, layanan);
            String ServiceDetail_Id = Integer.toString(detailID.getId());

            ServiceTransactionDetailDAO.insertStd(returnID, isFinished, ServiceDetail_Id, txtOrder.getText());

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem occurred while inserting pettype");
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
