package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import main.model.PetType;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class PetTypeController {

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
    public void handleButtonPetType (MouseEvent me){
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
        if(me.getSource() == addLabel) {
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

        if(me.getSource() == editLabel) {
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

        if(me.getSource() == deleteLabel) {
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
            DialogShowInfo("Error occurred while getting pet type information. Check your database.");
            throw e;
        }
    }

    @FXML
    private void initialize () throws SQLException, ClassNotFoundException {

        ptId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        ptType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        searchPetTypes(getEvent);
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
            DialogShowInfo("No type with name " + txtCari.getText());
        }
    }

    @FXML
    private void populatePetTypes (ObservableList < PetType > ptData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(ptData);
    }

    @FXML
    private void deletePetType (ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtID.getText().isEmpty()) {
            DialogShowInfo("Fields cannot be empty");
        } else if (!txtID.getText().matches("[0-9]+")) {
            DialogShowInfo("ID can only contain numbers.");
        } else {
            try {
                PetTypeDAO.deletePtWithId(txtID.getText());

            } catch (SQLException e) {
                DialogShowInfo("Problem occurred while deleting pettype. Check your database connection.");
            }
        }
    }

    @FXML
    private void updatePetType (ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtID.getText().isEmpty() || txtTipe.getText().isEmpty()) {
            DialogShowInfo("Fields cannot be empty");
        } else if (!txtID.getText().matches("[0-9]+")) {
            DialogShowInfo("ID can only contain numbers.");
        } else {
            try {
                PetTypeDAO.updateEntries(returnID, txtID.getText(), txtTipe.getText());

            } catch (SQLException e) {
                DialogShowInfo("Problem occurred while updating pet type. Check your database connection");
            }
        }
    }

    @FXML
    private void insertPetType (ActionEvent event) throws SQLException, ClassNotFoundException {

        if (txtID.getText().isEmpty()) {
            DialogShowInfo("Fields cannot be empty");
        }
        else {
            try {
                PetTypeDAO.insertPt(returnID, txtTipe.getText());

            } catch (SQLException e) {
                DialogShowInfo("Problem occurred while inserting pet type. Check your database connection");
            }
        }
    }

    @FXML
    private void selectedRow (MouseEvent me) throws ClassNotFoundException, SQLException {

        if(me.getClickCount() > 1)
        {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() {

        if(tableAll.getSelectionModel().getSelectedItem() != null) {
            PetType petType = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(petType.getId()));
            txtTipe.setText(petType.getType());
        }
    }

    @FXML
    private void clearFields(ActionEvent ae) {
        txtID.clear();
        txtTipe.clear();
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
