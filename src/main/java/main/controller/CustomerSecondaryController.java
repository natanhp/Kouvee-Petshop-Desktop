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
import main.dao.CustomerDAO;
import main.model.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class CustomerSecondaryController {

    private static String returnID;
    private static String returnRole;

    @FXML
    private TableColumn<Customer, Integer> cusId;

    @FXML
    private Button btnHapus;

    @FXML
    private TextField txtTelp;

    @FXML
    private TextField txtNama;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private TextField txtTglLahir;

    @FXML
    private TextField txtID;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private TableView<Customer> tableAll;

    @FXML
    private TextField txtAlamat;

    @FXML
    private TableColumn<Customer, String> cusName;

    @FXML
    private Button btnCustomerKeluar;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<Customer, String> cusAddress;

    @FXML
    private TableColumn<Customer, Date> cusDateBirth;

    @FXML
    private TableColumn<Customer, String> cusPhoneNumber;

    @FXML
    private TextField txtCari;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public void handleButtonCustomer (MouseEvent me){
        if (me.getSource() == btnCustomerKeluar)
            System.exit(0);

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
    }

    //Show All Customers
    @FXML
    private void searchCustomers (ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get all Customer information
            ObservableList<Customer> cusData = CustomerDAO.searchCustomers();

            //Populate Customers on TableView
            populateCustomers(cusData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting customers information from DB " + e);
            throw e;
        }
    }

    //Search a Customer
    @FXML
    private void searchCustomer (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Get Customer Information
            Customer cus = CustomerDAO.searchCustomer(txtCari.getText());

            //Populate Customer on TableView and Display on TextField
            populateAndShowCustomer(cus);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Customer information from DB" + e);
            throw e;
        }
    }

    @FXML
    private void initialize () {

        cusId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        cusName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        cusDateBirth.setCellValueFactory(cellData -> cellData.getValue().dateBirthProperty());
        cusAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        cusPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
    }

    //Populate Customers
    @FXML
    private void populateCustomer (Customer cus) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<Customer> cusData = FXCollections.observableArrayList();
        //Add customer to the ObservableList
        cusData.add(cus);
        //Set items to the tableAll
        tableAll.setItems(cusData);
    }

    @FXML
    private void populateAndShowCustomer (Customer cus) throws ClassNotFoundException {
        if (cus != null) {
            populateCustomer(cus);
        } else {
            System.out.println("This customer doesn't exist");
        }
    }

    @FXML
    private void populateCustomers (ObservableList < Customer > cusData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(cusData);
    }

    @FXML
    private void deleteCustomer (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            CustomerDAO.deleteCusWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting customer");
        }
    }

    @FXML
    private void updateCustomer (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            CustomerDAO.updateEntries(returnID, txtID.getText(), txtNama.getText(), txtTglLahir.getText(), txtAlamat.getText(),
                    txtTelp.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating customer");
        }
    }

    @FXML
    private void insertCustomer (ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            CustomerDAO.insertCus(returnID, txtNama.getText(), txtTglLahir.getText(), txtAlamat.getText(),
                    txtTelp.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting customer");
        }
    }
}
