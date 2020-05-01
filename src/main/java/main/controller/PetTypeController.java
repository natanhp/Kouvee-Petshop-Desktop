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
import main.dao.PetTypeDAO;
import main.model.Employee;
import main.model.PetSize;
import main.model.PetType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class PetTypeController implements Initializable {
    private static String returnID;
    private static String returnRole;
    private static ActionEvent getEvent;

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
    private TableView<PetType> tableAll;

    @FXML
    private Button btnTipeKeluar;

    @FXML
    private TextField txtTipe;

    @FXML
    private TableColumn<PetType, String> ptType;

    @FXML
    private Label addLabel;

    @FXML
    private Label editLabel;

    @FXML
    private Label deleteLabel;

    @FXML
    private ImageView addLogo;

    @FXML
    private ImageView editLogo;

    @FXML
    private ImageView deleteLogo;

    @FXML
    private TextField txtCari;

    @FXML
    private TableColumn<PetType, Timestamp> ptCreatedAt;

    @FXML
    private TableColumn<PetType, Timestamp> ptUpdatedAt;

    @FXML
    private TableColumn<PetType, Timestamp> ptDeletedAt;

    @FXML
    private TableColumn<PetType, String> ptCreatedBy;

    @FXML
    private TableColumn<PetType, String> ptUpdatedBy;

    @FXML
    private TableColumn<PetType, String> ptDeletedBy;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public static void getEvent(ActionEvent ae) {
        getEvent = ae;
    }

    @FXML
    public void handleButtonPetType(MouseEvent me) {
        if (me.getSource() == btnTipeKeluar) {
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
            txtTipe.setDisable(false);

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
            txtTipe.setDisable(false);

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
            txtTipe.setDisable(true);

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

    //Show All PetTypes
    @FXML
    private void searchPetTypes(ActionEvent event) {
        loadAllData();
    }

    //Search a PetType
    @FXML
    private void searchPetType(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Get PetType Information
            populatePetTypes(PetTypeDAO.searchPetType(txtCari.getText()));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting PetType information from DB" + e);
            DialogShowInfo("Error occurred while getting pet type information. Check your database.");
            throw e;
        }
    }

    //Populate PetTypes
    @FXML
    private void populatePetType(PetType pt) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<PetType> ptData = FXCollections.observableArrayList();
        //Add pettype to the ObservableList
        ptData.add(pt);
        //Set items to the tableAll
        tableAll.setItems(ptData);
    }

    @FXML
    private void populateAndShowPetType(PetType pt) throws ClassNotFoundException {
        if (pt != null) {
            populatePetType(pt);
        } else {
            System.out.println("This pettype doesn't exist");
            DialogShowInfo("No type with name " + txtCari.getText());
        }
    }

    @FXML
    private void populatePetTypes(ObservableList<PetType> ptData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(ptData);
    }

    @FXML
    private void deletePetType(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            PetTypeDAO.softDeletePtWithId(returnID, txtID.getText());
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting pettype");
        }
    }

    @FXML
    private void updatePetType(ActionEvent event) throws SQLException, ClassNotFoundException {
        String petType = txtTipe.getText().trim();
        String id = txtID.getText().trim();

        if (petType.equals("") || id.equals("")) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }

        try {
            PetTypeDAO.updateEntries(returnID, id, petType);
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating pettype");
        }
    }

    @FXML
    private void insertPetType(ActionEvent event) {
        String petType = txtTipe.getText().trim();

        if (petType.equals("")) {
            return;
        }

        try {
            PetTypeDAO.insertPt(returnID, petType);

            loadAllData();

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting pettype");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ptId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        ptType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        ptCreatedAt.setCellValueFactory(cellData -> cellData.getValue().createdAtProperty());
        ptUpdatedAt.setCellValueFactory(cellData -> cellData.getValue().updatedAtProperty());
        ptDeletedAt.setCellValueFactory(cellData -> cellData.getValue().deletedAtProperty());
        ptCreatedBy.setCellValueFactory(cellData -> cellData.getValue().createdByProperty());
        ptUpdatedBy.setCellValueFactory(cellData -> cellData.getValue().updatedByProperty());
        ptDeletedBy.setCellValueFactory(cellData -> cellData.getValue().deletedByProperty());

        loadAllData();
    }

    private void loadAllData() {
        ObservableList<PetType> ptData = null;
        try {
            ptData = PetTypeDAO.searchPetTypes();
            populatePetTypes(ptData);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void clearFields() {
        txtCari.clear();
        txtID.clear();
        txtTipe.clear();

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
            PetType petType = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(petType.getId()));
            txtTipe.setText(petType.getType());
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
