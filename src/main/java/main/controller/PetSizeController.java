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
import main.model.PetSize;
import main.model.PetSize;

import java.io.IOException;
import java.sql.SQLException;

public class PetSizeController {

    private static String returnID;
    private static String returnRole;

    @FXML
    private Button btnHapus;

    @FXML
    private TableColumn<PetSize, Integer> psId;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private TextField txtID;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private TableView<PetSize> tableAll;

    @FXML
    private Button btnUkuranKeluar;

    @FXML
    private TextField txtUkuran;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<PetSize, String> psSize;

    @FXML
    private TextField txtCari;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public void handleButtonUkuran (MouseEvent me){
        if (me.getSource() == btnUkuranKeluar)
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

    //Show All PetSizes
    @FXML
    private void searchPetSizes (ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get all PetSize information
            ObservableList<PetSize> psData = PetSizeDAO.searchPetSizes();

            //Populate PetSizes on TableView
            populatePetSizes(psData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting petsize information from DB " + e);
            throw e;
        }
    }

    //Search a PetSize
    @FXML
    private void searchPetSize (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Get PetSize Information
            PetSize ps = PetSizeDAO.searchPetSize(txtCari.getText());

            //Populate PetSize on TableView and Display on TextField
            populateAndShowPetSize(ps);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting PetSize information from DB" + e);
            throw e;
        }
    }

    @FXML
    private void initialize () {

        psId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        psSize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
    }

    //Populate PetSizes
    @FXML
    private void populatePetSize (PetSize ps) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<PetSize> psData = FXCollections.observableArrayList();
        //Add petsize to the ObservableList
        psData.add(ps);
        //Set items to the tableAll
        tableAll.setItems(psData);
    }

    @FXML
    private void populateAndShowPetSize (PetSize ps) throws ClassNotFoundException {
        if (ps != null) {
            populatePetSize(ps);
        } else {
            System.out.println("This petsize doesn't exist");
        }
    }

    @FXML
    private void populatePetSizes (ObservableList < PetSize > psData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(psData);
    }

    @FXML
    private void deletePetSize (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            PetSizeDAO.deletePsWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting petsize");
        }
    }

    @FXML
    private void updatePetSize (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            PetSizeDAO.updateEntries(returnID, txtID.getText(), txtUkuran.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating petsize");
        }
    }

    @FXML
    private void insertPetSize (ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            PetSizeDAO.insertPs(returnID, txtUkuran.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting petsize");
        }
    }
}
