package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.dao.EmployeeDAO;
import main.dao.PetSizeDAO;
import main.dao.PetTypeDAO;
import main.model.*;

import java.sql.SQLException;
import java.util.Date;

public class PetController {

    private final ObservableList selectBox = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Pet, Integer> petId;

    @FXML
    private Button btnHapus;

    @FXML
    private TextField txtTelp;

    @FXML
    private ComboBox<PetType> comboTipe;

    @FXML
    private TableColumn<Pet, Integer> petType;

    @FXML
    private TextField txtNama;

    @FXML
    private TableColumn<Pet, Integer> petOwner;

    @FXML
    private TableColumn<Pet, Date> petDateBirth;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnHewanKeluar;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private TableColumn<Pet, String> petName;

    @FXML
    private TextField txtID;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private TableView<Pet> tableAll;

    @FXML
    private TextField txtAlamat;

    @FXML
    private ComboBox<PetSize> comboUkuran;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<Pet, Integer> petSize;

    @FXML
    private TextField txtCari;

    @FXML
    void searchPet(ActionEvent event) {

    }

    @FXML
    void searchPets(ActionEvent event) {

    }

    @FXML
    private void initialize() {

        petId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        petName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        petDateBirth.setCellValueFactory(cellData -> cellData.getValue().dateBirthProperty());
        petOwner.setCellValueFactory(cellData -> cellData.getValue().customer_IdProperty().asObject());
        petType.setCellValueFactory(cellData -> cellData.getValue().petType_IdProperty().asObject());
        petSize.setCellValueFactory(cellData -> cellData.getValue().petSize_IdProperty().asObject());

//        comboTipe.setItems();
    }

    @FXML
    void deletePet(ActionEvent event) {

    }

    @FXML
    void updatePet(ActionEvent event) {

    }

    @FXML
    void insertPet(ActionEvent event) {

    }

    @FXML
    private void selectType(ActionEvent event) throws SQLException, ClassNotFoundException {

        comboTipe.setMaxHeight(20);

        try {
            //Try getting all the PetTypes information
            ObservableList<PetType> typeData = PetTypeDAO.searchPetTypes();

        } catch (SQLException e) {
            System.out.println("Error occurred while getting all pettypes information from DB " + e);
            throw e;
        }

//        String query = "SELECT id FROM pettypes";
//        pst
    }

    @FXML
    void selectSize(ActionEvent event) {

    }

    @FXML
    void handleButtonSupplier(ActionEvent event) {

    }

    @FXML
    void e1d42b(ActionEvent event) {

    }
}
