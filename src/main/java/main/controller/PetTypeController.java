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
import main.dao.PetTypeDAO;
import main.model.PetType;
import main.model.PetType;

import java.io.IOException;
import java.sql.SQLException;

public class PetTypeController {
    private static String returnID;
    private static String returnRole;

    @FXML
    private Button btnHapus;

    @FXML
    private TableColumn<PetType, Integer> ptId;

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
    private TableView<PetType> tableAll;

    @FXML
    private Button btnTipeKeluar;

    @FXML
    private TextField txtTipe;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<PetType, String> ptType;

    @FXML
    private TextField txtCari;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public void handleButtonTipe (MouseEvent me){
        if (me.getSource() == btnTipeKeluar)
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

    //Show All PetTypes
    @FXML
    private void searchPetTypes (ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get all PetType information
            ObservableList<PetType> ptData = PetTypeDAO.searchPetTypes();

            //Populate PetTypes on TableView
            populatePetTypes(ptData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting pettype information from DB " + e);
            throw e;
        }
    }

    //Search a PetType
    @FXML
    private void searchPetType (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Get PetType Information
            PetType pt = PetTypeDAO.searchPetType(txtCari.getText());

            //Populate PetType on TableView and Display on TextField
            populateAndShowPetType(pt);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting PetType information from DB" + e);
            throw e;
        }
    }

    @FXML
    private void initialize () {

        ptId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        ptType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    }

    //Populate PetTypes
    @FXML
    private void populatePetType (PetType pt) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<PetType> ptData = FXCollections.observableArrayList();
        //Add pettype to the ObservableList
        ptData.add(pt);
        //Set items to the tableAll
        tableAll.setItems(ptData);
    }

    @FXML
    private void populateAndShowPetType (PetType pt) throws ClassNotFoundException {
        if (pt != null) {
            populatePetType(pt);
        } else {
            System.out.println("This pettype doesn't exist");
        }
    }

    @FXML
    private void populatePetTypes (ObservableList < PetType > ptData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(ptData);
    }

    @FXML
    private void deletePetType (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            PetTypeDAO.deletePtWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting pettype");
        }
    }

    @FXML
    private void updatePetType (ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            PetTypeDAO.updateEntries(returnID, txtID.getText(), txtTipe.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating pettype");
        }
    }

    @FXML
    private void insertPetType (ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            PetTypeDAO.insertPt(returnID, txtTipe.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting pettype");
        }
    }
}
