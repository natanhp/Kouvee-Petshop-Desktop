package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.dao.PetSizeDAO;
import main.dao.ServiceDAO;
import main.model.PetSize;
import main.model.Service;

import java.io.IOException;
import java.sql.SQLException;


public class ServiceController {

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
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private TableView<Service> tableAll;

    @FXML
    private TextField txtLayanan;

    @FXML
    private Button btnLihat;

    @FXML
    private TextField txtCari;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    @FXML
    void handleButtonLayanan(MouseEvent me) {

        if (me.getSource() == btnLayananKeluar)
            System.exit(0);

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
    void searchServices(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get all Service information
            ObservableList<Service> sData = ServiceDAO.searchServices();

            //Populate Services on TableView
            populateServices(sData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting petsize information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void initialize() {

        sId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        sService.setCellValueFactory(cellData -> cellData.getValue().serviceNameProperty());
    }

    //Populate services
    @FXML
    private void populateService(Service s) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<Service> sData = FXCollections.observableArrayList();
        //Add service to the ObservableList
        sData.add(s);
        //Set items to the tableAll
        tableAll.setItems(sData);
    }

    @FXML
    private void populateAndShowService(Service s) throws ClassNotFoundException {

        if(s != null) {
            populateService(s);
        } else {
            System.out.println("This service doesn't exists");
        }
    }

    @FXML
    private void populateServices(ObservableList<Service> sData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(sData);
    }

    @FXML
    void deleteService(ActionEvent event) throws SQLException, ClassNotFoundException{

        try {
            ServiceDAO.deleteSWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting service");
        }
    }

    @FXML
    void updateService(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            ServiceDAO.updateEntries(returnID, txtID.getText(), txtLayanan.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating service");
        }
    }

    @FXML
    void insertService(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            ServiceDAO.insertS(returnID, txtLayanan.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting service");
        }
    }

    @FXML
    void e1d42b(ActionEvent event) {

    }

}
