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
import main.dao.PetSizeDAO;
import main.model.Employee;
import main.model.PetSize;
import main.model.PetSize;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class PetSizeController {

    private static String returnID;
    private static String returnRole;
    private static ActionEvent getEvent;

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
    public void handleButtonPetSize (MouseEvent me){
        if (me.getSource() == btnUkuranKeluar) {
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
            txtUkuran.setDisable(false);

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
            txtUkuran.setDisable(false);

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
            txtUkuran.setDisable(true);

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
            DialogShowInfo("Error occurred while getting pet size information. Check your database.");
            throw e;
        }
    }

    @FXML
    private void initialize () throws SQLException, ClassNotFoundException {

        psId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        psSize.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        searchPetSizes(getEvent);
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
            DialogShowInfo("Pet size not found with size name" + txtCari.getText());
        }
    }

    @FXML
    private void populatePetSizes (ObservableList < PetSize > psData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(psData);
    }

    @FXML
    private void deletePetSize (ActionEvent event) throws SQLException, ClassNotFoundException {
        if (txtID.getText().isEmpty()) {
            DialogShowInfo("Fields cannot be empty");
        } else if (!txtID.getText().matches("[0-9]+")) {
            DialogShowInfo("ID can only contain numbers.");
        } else {
            try {
                PetSizeDAO.deletePsWithId(txtID.getText());

            } catch (SQLException e) {
                DialogShowInfo("Problem occurred while deleting petsize. Check your database connection.");
            }
        }
    }

    @FXML
    private void updatePetSize (ActionEvent event) throws SQLException, ClassNotFoundException {

        if (txtID.getText().isEmpty() || txtUkuran.getText().isEmpty()) {
            DialogShowInfo("Fields cannot be empty");
        } else if (!txtID.getText().matches("[0-9]+")) {
            DialogShowInfo("ID can only contain numbers.");
        } else {
            try {
                PetSizeDAO.updateEntries(returnID, txtID.getText(), txtUkuran.getText());

            } catch (SQLException e) {
                DialogShowInfo("Problem occurred while updating petsize. Check your database connection.");
            }
        }
    }

    @FXML
    private void insertPetSize (ActionEvent event) throws SQLException, ClassNotFoundException {

        if (txtUkuran.getText().isEmpty()) {
            DialogShowInfo("Fields cannot be empty");
        } else {
            try {
                PetSizeDAO.insertPs(returnID, txtUkuran.getText());

            } catch (SQLException e) {
                DialogShowInfo("Problem occurred while inserting petsize. Check your database connection.");
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
            PetSize petSize = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(petSize.getId()));
            txtUkuran.setText(petSize.getSize());
        }
    }

    @FXML
    private void clearFields(ActionEvent ae) {
        txtID.clear();
        txtUkuran.clear();
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
