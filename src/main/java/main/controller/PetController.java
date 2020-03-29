package main.controller;

import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import main.dao.PetDAO;
import main.dao.PetSizeDAO;
import main.dao.PetTypeDAO;
import main.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class PetController {

    private static String returnID;
    private static String returnRole;

    @FXML
    private TableColumn<Pet, Integer> petId;

    @FXML
    private Button btnHapus;

    @FXML
    private TextField txtTglLahir;

    @FXML
    private ComboBox<PetType> comboTipe;

    @FXML
    private TableColumn<Pet, String> petType;

    @FXML
    private TextField txtNama;

    @FXML
    private TableColumn<Pet, String> petOwner;

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
    private TextField txtOwner;

    @FXML
    private TableView<Pet> tableAll;

    @FXML
    private ComboBox<PetSize> comboUkuran;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<Pet, String> petSize;

    @FXML
    private TextField txtCari;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public void handleButtonPet (MouseEvent me){
        if (me.getSource() == btnHewanKeluar)
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


    //Search a Pet
    @FXML
    void searchPet(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Get PetType Information
            Pet p = PetDAO.searchPet(txtCari.getText());

            //Populate PetType on TableView and Display on TextField
            populateAndShowPet(p);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Pet information from DB" + e);
            throw e;
        }
    }

    //Search all Pets
    @FXML
    void searchPets(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get all PetType information
            ObservableList<Pet> pData = PetDAO.searchPets();

            //Populate PetTypes on TableView
            populatePets(pData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting pet information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void populateAndShowPet (Pet p) throws ClassNotFoundException {
        if (p != null) {
            populatePet(p);
        } else {
            System.out.println("This pet doesn't exist");
        }
    }

    //Populate Pets
    @FXML
    private void populatePet (Pet p) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<Pet> pData = FXCollections.observableArrayList();
        //Add pet to the ObservableList
        pData.add(p);
        //Set items to the tableAll
        tableAll.setItems(pData);
    }


    @FXML
    private void populatePets (ObservableList <Pet> pData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(pData);
    }

    @FXML
    private void initialize() {

        petId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        petName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        petDateBirth.setCellValueFactory(cellData -> cellData.getValue().dateBirthProperty());
        petOwner.setCellValueFactory(cellData -> cellData.getValue().customer_nameProperty());
        petType.setCellValueFactory(cellData -> cellData.getValue().petType_nameProperty());
        petSize.setCellValueFactory(cellData -> cellData.getValue().petSize_nameProperty());

        ObservableList typeList = FXCollections.observableArrayList();
        comboUkuran.getItems().clear();
        comboTipe.getItems().clear();
        comboTipe.setItems(typeList);
        comboUkuran.setItems(typeList);
    }


    @FXML
    void deletePet(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            PetDAO.deletePetWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting pete");
        }
    }

    @FXML
    void updatePet(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {
            String tipe = Integer.toString(comboTipe.getValue().getId());
            String ukr = Integer.toString(comboUkuran.getValue().getId());

            Customer owner = PetDAO.searchOwner(txtOwner.getText());
            String Customers_id = Integer.toString(owner.getId());

            PetDAO.updateEntries(returnID, txtID.getText(), txtNama.getText(), txtTglLahir.getText(), Customers_id, tipe, ukr);

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting pet");
        }
    }

    @FXML
    void insertPet(ActionEvent event) throws SQLException, ClassNotFoundException{
        try {

            String tipe = Integer.toString(comboTipe.getValue().getId());
            String ukr = Integer.toString(comboUkuran.getValue().getId());

            Customer owner = PetDAO.searchOwner(txtOwner.getText());
            String Customers_id = Integer.toString(owner.getId());

            System.out.println("Customers ID : "+Customers_id);

            PetDAO.insertPet(returnID, txtNama.getText(), txtTglLahir.getText(), Customers_id
                    ,tipe, ukr);


        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting pettype");
        }
    }

    @FXML
    private void pickerType(MouseEvent me) throws SQLException, ClassNotFoundException {

        comboTipe.setMaxHeight(20);

        try {
            //Try getting all the PetTypes and PetSizes information
            ObservableList<PetType> typeData = PetTypeDAO.searchPetTypes();
            ObservableList<PetSize> sizeData = PetSizeDAO.searchPetSizes();

            //Populate PetTypes and PetSizes on ComboBox
            populatePetTypeComboBox(typeData);
            populatePetSizeComboBox(sizeData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all pettypes information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void selectType(ActionEvent ae) throws SQLException, ClassNotFoundException {

    }

    @FXML
    private void populatePetTypeComboBox(ObservableList<PetType> typeData) throws SQLException, ClassNotFoundException {

        //Set items to the comboBox
        comboTipe.setItems(typeData);
        comboTipe.setConverter(new StringConverter<PetType>() {

            @Override
            public String toString(PetType object) {
                return object.getType();
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
    private void populatePetSizeComboBox(ObservableList<PetSize> typeData) throws SQLException, ClassNotFoundException {

        //Set items to the comboBox
        comboUkuran.setItems(typeData);
        comboUkuran.setConverter(new StringConverter<PetSize>() {

            @Override
            public String toString(PetSize object) {
                return object.getSize();
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

    }



    @FXML
    void selectSize(ActionEvent event) {

    }

    @FXML
    void e1d42b(ActionEvent event) {

    }
}
