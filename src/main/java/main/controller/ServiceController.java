package main.controller;

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
import main.dao.ServiceDAO;
import main.model.Employee;
import main.model.Service;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ServiceController implements Initializable {

    private static String returnID;
    private static String returnRole;

    @FXML
    private Button btnLayananKeluar;

    @FXML
    private TableColumn<Service, String> sService;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private TableColumn<Service, Integer> sId;

    @FXML
    private TextField txtID;

    @FXML
    private TableView<Service> tableAll;

    @FXML
    private TextField txtLayanan;

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
            txtLayanan.setDisable(false);

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
            txtLayanan.setDisable(false);

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
            txtLayanan.setDisable(true);

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

    //Search a Service
    @FXML
    void searchService(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get Service Information
            Service s = ServiceDAO.searchService(txtCari.getText());

            //Populate PetSize on TableView and Display on TextField
            populateAndShowService(s);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting PetSize information from DB" + e);
            throw e;
        }
    }

    //Show all Services
    @FXML
    void searchServices(ActionEvent event) {
        loadAllData();
    }

    //Populate services
    @FXML
    private void populateService(Service s) {

        //Declare an ObservableList for TableView
        ObservableList<Service> sData = FXCollections.observableArrayList();
        //Add service to the ObservableList
        sData.add(s);
        //Set items to the tableAll
        tableAll.setItems(sData);
    }

    @FXML
    private void populateAndShowService(Service s) throws ClassNotFoundException {

        if (s != null) {
            populateService(s);
        } else {
            System.out.println("This service doesn't exists");
        }
    }

    @FXML
    private void populateServices(ObservableList<Service> sData) {

        //Set items to the tableAll
        tableAll.setItems(sData);
    }

    @FXML
    void deleteService(ActionEvent event) throws ClassNotFoundException {

        try {
            ServiceDAO.softDeleteSWithId(returnID, txtID.getText());
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting service");
        }
    }

    @FXML
    void updateService(ActionEvent event) {
        String serviceName = txtLayanan.getText().trim();
        String id = txtID.getText().trim();

        if (serviceName.equals("") || serviceName.equals("")) {
            return;
        }

        try {
            ServiceDAO.updateEntries(returnID, id, serviceName);

            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating service");
        }
    }

    @FXML
    void insertService(ActionEvent event) throws ClassNotFoundException {
        String serviceName = txtLayanan.getText().trim();

        if (serviceName.equals("")) {
            return;
        }

        try {
            ServiceDAO.insertS(returnID, serviceName);

            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting service");
        }
    }

    @FXML
    void e1d42b(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        sService.setCellValueFactory(cellData -> cellData.getValue().serviceNameProperty());

        loadAllData();
    }

    private void loadAllData() {
                    //Get all Service information
        ObservableList<Service> sData = null;
        try {
            sData = ServiceDAO.searchServices();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        //Populate Services on TableView
            populateServices(sData);

    }

    @FXML
    public void clearFields() {
        txtCari.clear();
        txtID.clear();
        txtLayanan.clear();

        loadAllData();
    }

    @FXML
    private void selectedRow(MouseEvent me) {

        if (me.getClickCount() > 1) {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() {


        if (tableAll.getSelectionModel().getSelectedItem() != null) {
            Service service = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(service.getId()));
            txtLayanan.setText(service.getServiceName());
        }
    }
}
