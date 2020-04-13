package main.controller;

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
import main.dao.PetSizeDAO;
import main.dao.PetTypeDAO;
import main.dao.ServiceDAO;
import main.dao.ServiceDetailDAO;
import main.model.PetSize;
import main.model.PetType;
import main.model.Service;
import main.model.ServiceDetail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServiceDetailController implements Initializable {

    private static String returnID;
    private static String returnRole;

    @FXML
    private TableColumn<ServiceDetail, Integer> serviceId;

    @FXML
    private Button btnHapus;

    @FXML
    private ComboBox<PetType> comboTipe;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableColumn<ServiceDetail, Double> petDateBirth;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnHewanKeluar;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private TableColumn<ServiceDetail, String> petName;

    @FXML
    private TextField txtID;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private ComboBox<Service> comboService;

    @FXML
    private TableView<ServiceDetail> tableAll;

    @FXML
    private ComboBox<PetSize> comboUkuran;

    @FXML
    private Button btnLihat;

    @FXML
    private TextField txtCari;

    @FXML
    private Label addLabel;

    @FXML
    private Label editLabel;

    @FXML
    private ImageView deleteLogo;

    @FXML
    private ImageView addLogo;

    @FXML
    private Label deleteLabel;

    @FXML
    private ImageView editLogo;

    private ObservableList<ServiceDetail> serviceDetails = null;


    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    @FXML
    void handleButtonPet(MouseEvent me) {
        if (me.getSource() == btnHewanKeluar) {
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

        if (me.getSource() == btnMenuUtama) {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/MainMenu.fxml")));
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
        if (me.getSource() == addLabel) {
            btnPerbarui.setDisable(true);
            btnTambah.setDisable(false);
            btnHapus.setDisable(true);
            txtID.setDisable(true);
            txtPrice.setDisable(false);
            comboService.setDisable(false);
            comboUkuran.setDisable(false);
            comboTipe.setDisable(false);

            addLabel.setTextFill(Color.WHITE);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_white_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if (me.getSource() == editLabel) {
            btnPerbarui.setDisable(false);
            btnTambah.setDisable(true);
            btnHapus.setDisable(true);
            txtID.setDisable(false);
            txtPrice.setDisable(false);
            comboService.setDisable(false);
            comboUkuran.setDisable(false);
            comboTipe.setDisable(false);

            addLabel.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.WHITE);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_white_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if (me.getSource() == deleteLabel) {
            btnPerbarui.setDisable(true);
            btnTambah.setDisable(true);
            btnHapus.setDisable(false);
            txtID.setDisable(false);
            txtPrice.setDisable(true);
            comboService.setDisable(true);
            comboUkuran.setDisable(true);
            comboTipe.setDisable(true);

            addLabel.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.WHITE);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_white_18dp.png"));
            addLogo.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }
    }


    @FXML
    void searchServiceDetail(ActionEvent event) {
        if (serviceDetails != null) {
            ObservableList<ServiceDetail> serviceDetailsBak = FXCollections.observableArrayList();
            for (ServiceDetail serviceDetail : serviceDetails) {
                String serviceDetailName = serviceDetail.getCompleteName().toLowerCase();
                if (serviceDetailName.contains(txtCari.getText().toLowerCase())) {
                    serviceDetailsBak.add(serviceDetail);
                }
            }

            try {
                populatePets(serviceDetailsBak);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void searchPets(ActionEvent event) {
        loadAllData();
    }

    @FXML
    private void populateAndShowPet(ServiceDetail p) {
        if (p != null) {
            populatePet(p);
        } else {
            System.out.println("This pet doesn't exist");
        }
    }

    //Populate Pets
    @FXML
    private void populatePet(ServiceDetail p) {

        //Declare an ObservableList for TableView
        ObservableList<ServiceDetail> pData = FXCollections.observableArrayList();
        //Add pet to the ObservableList
        pData.add(p);
        //Set items to the tableAll
        tableAll.setItems(pData);
    }


    @FXML
    private void populatePets(ObservableList<ServiceDetail> pData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(pData);
    }

    @FXML
    void deleteServiceDetail(ActionEvent event) throws ClassNotFoundException {
        try {
            ServiceDetailDAO.softDeletePetWithId(txtID.getText());
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting pet");
        }
    }

    @FXML
    void updateServiceDetail(ActionEvent event) throws ClassNotFoundException {
        String textPrice = txtPrice.getText().trim();
        String textId = txtID.getText().trim();
        if (comboService.getValue() == null ||
                comboTipe.getValue() == null || comboUkuran.getValue() == null || textPrice.equals("") ||
                textId.equals("")) {
            return;
        }

        double price = Double.parseDouble(txtPrice.getText().trim());
        int id = Integer.parseInt(textId);

        if (price <= 0) {
            return;
        }

        try {
            int tipe = comboTipe.getValue().getId();
            int ukr = comboUkuran.getValue().getId();
            int serviceId = comboService.getValue().getId();
            ;

            ServiceDetail serviceDetail = new ServiceDetail();
            serviceDetail.setId(id);
            serviceDetail.setPetSizeId(ukr);
            serviceDetail.setPetTypeId(tipe);
            serviceDetail.setServiceId(serviceId);
            serviceDetail.setPrice(price);

            ServiceDetailDAO.updateServiceDetail(returnID, serviceDetail);
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating pet");
        }
    }

    @FXML
    void insertServiceDetail(ActionEvent event) {
        String textPrice = txtPrice.getText().trim();
        if (comboService.getValue() == null ||
                comboTipe.getValue() == null || comboUkuran.getValue() == null || textPrice.equals("")) {
            return;
        }

        double price = Double.parseDouble(txtPrice.getText().trim());

        if (price <= 0) {
            return;
        }

        try {
            int tipe = comboTipe.getValue().getId();
            int ukr = comboUkuran.getValue().getId();
            int serviceId = comboService.getValue().getId();

            ServiceDetail serviceDetail = new ServiceDetail();
            serviceDetail.setPetSizeId(ukr);
            serviceDetail.setPetTypeId(tipe);
            serviceDetail.setServiceId(serviceId);
            serviceDetail.setPrice(price);

            ServiceDetailDAO.insertServiceDetail(returnID, serviceDetail);
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting pettype");
        }
    }

    @FXML
    private void selectType(MouseEvent me) throws SQLException, ClassNotFoundException {
        comboTipe.setMaxHeight(20);

        try {
            //Try getting all the PetTypes information
            ObservableList<PetType> typeData = PetTypeDAO.searchPetTypes();

            //Populate PetTypes on ComboBox
            populatePetTypeComboBox(typeData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all pettypes information from DB " + e);
            throw e;
        }
    }

    @FXML
    void selectSize(MouseEvent me) throws SQLException, ClassNotFoundException {
        comboTipe.setMaxHeight(20);

        try {
            //Try getting all the PetTypes and PetSizes information
            ObservableList<PetSize> sizeData = PetSizeDAO.searchPetSizes();

            //Populate PetTypes and PetSizes on ComboBox
            populatePetSizeComboBox(sizeData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all petsize information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void populatePetTypeComboBox(ObservableList<PetType> typeData) throws SQLException {

        //Set items to the comboBox
        if (comboTipe == null) {
            return;
        }

        comboTipe.setItems(typeData);
        comboTipe.setConverter(new StringConverter<PetType>() {

            @Override
            public String toString(PetType object) {
                if (object != null) {
                    return object.getType();
                }

                return "";
            }

            public int getObjectID(PetType object) {
                return object.getId();
            }

            @Override
            public PetType fromString(String string) {
                // TODO Auto-generated method stub
                return null;
            }
        });

        comboTipe.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PetType>() {
            @Override
            public void changed(ObservableValue<? extends PetType> observable, PetType oldValue, PetType newValue) {

            }
        });

    }

    @FXML
    private void populatePetSizeComboBox(ObservableList<PetSize> typeData) throws SQLException {

        //Set items to the comboBox
        if (comboUkuran == null) {
            return;
        }

        comboUkuran.setItems(typeData);
        comboUkuran.setConverter(new StringConverter<PetSize>() {

            @Override
            public String toString(PetSize object) {
                if (object != null) {
                    return object.getSize();
                }

                return "";
            }

            public int getObjectID(PetSize object) {
                return object.getId();
            }

            @Override
            public PetSize fromString(String string) {
                // TODO Auto-generated method stub
                return null;
            }
        });

        comboUkuran.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PetSize>() {
            @Override
            public void changed(ObservableValue<? extends PetSize> observable, PetSize oldValue, PetSize newValue) {

            }
        });

    }

    @FXML
    private void selectedRow(MouseEvent me) throws ClassNotFoundException, SQLException {

        if (me.getClickCount() > 1) {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() throws SQLException, ClassNotFoundException {
        if (tableAll.getSelectionModel().getSelectedItem() != null) {
            ServiceDetail serviceDetail = tableAll.getSelectionModel().getSelectedItem();

            ObservableList<PetType> typeData = PetTypeDAO.searchPetTypes();
            populatePetTypeComboBox(typeData);
            ObservableList<PetSize> sizeData = PetSizeDAO.searchPetSizes();
            populatePetSizeComboBox(sizeData);
            ObservableList<Service> customerData = ServiceDAO.searchServices();
            populateCustomerComboBox(customerData);


            txtID.setText(Integer.toString(serviceDetail.getId()));
            txtPrice.setText(String.valueOf(serviceDetail.getPrice()));
            comboService.getItems();
            comboTipe.getItems();
            comboUkuran.getItems();

            for (PetType petType : comboTipe.getItems()) {
                if (petType.getId() == serviceDetail.getPetTypeId()) {
                    comboTipe.getSelectionModel().select(petType);
                }
            }

            for (PetSize petSize : comboUkuran.getItems()) {
                if (petSize.getId() == serviceDetail.getPetSizeId()) {
                    comboUkuran.getSelectionModel().select(petSize);
                }
            }

            for (Service service : comboService.getItems()) {
                if (service.getId() == serviceDetail.getServiceId()) {
                    comboService.getSelectionModel().select(service);
                }
            }
        }
    }

    @FXML
    private void clearFields(ActionEvent ae) {
        txtID.clear();
        txtPrice.clear();
        comboUkuran.setValue(null);
        comboTipe.setValue(null);
        comboService.setValue(null);
        txtCari.clear();

        loadAllData();
    }

    @FXML
    void e1d42b(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serviceId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        petName.setCellValueFactory(cellData -> cellData.getValue().completeNameProperty());
        petDateBirth.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        ObservableList typeList = FXCollections.observableArrayList();
        comboUkuran.getItems().clear();
        comboTipe.getItems().clear();
        comboService.getItems().clear();
        comboService.setItems(typeList);
        comboTipe.setItems(typeList);
        comboUkuran.setItems(typeList);

        loadAllData();
    }

    private void loadAllData() {
        //Get all PetType information
        try {
            serviceDetails = ServiceDetailDAO.getServiceDetails();
            //Populate PetTypes on TableView
            populatePets(serviceDetails);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private void populateCustomerComboBox(ObservableList<Service> typeData) {

        //Set items to the comboBox
        if (comboService == null) {
            return;
        }

        comboService.setItems(typeData);
        comboService.setConverter(new StringConverter<Service>() {

            @Override
            public String toString(Service object) {
                if (object != null) {
                    return object.getServiceName();
                }

                return "";
            }

            public int getObjectID(Service object) {
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
    void selectCustomer(MouseEvent me) throws SQLException, ClassNotFoundException {
        comboService.setMaxHeight(20);

        try {
            //Try getting all the PetTypes and PetSizes information
            ObservableList<Service> sizeData = ServiceDAO.searchServices();

            //Populate PetTypes and PetSizes on ComboBox
            populateCustomerComboBox(sizeData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all petsize information from DB " + e);
            throw e;
        }
    }
}
