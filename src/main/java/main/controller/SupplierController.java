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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.dao.SupplierDAO;
import main.model.Supplier;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class SupplierController {

    private static String returnID;
    private static String returnRole;
    private static ActionEvent getEvent;

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
    private TextField txtID;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private TableView<Supplier> tableAll;

    @FXML
    private TextField txtAlamat;

    @FXML
    private TableColumn<Supplier, String> sprName;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<Supplier, Integer> sprId;

    @FXML
    private TableColumn<Supplier, String> sprAddress;

    @FXML
    private TableColumn<Supplier, String> sprPhoneNumber;

    @FXML
    private Button btnSupplierKeluar;

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

    public static void getEvent(ActionEvent ae) {
        getEvent = ae;
    }

    @FXML
    public void handleButtonSupplier(MouseEvent me) {

        if(me.getSource() == btnSupplierKeluar) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Kouvee PetShop");
            alert.setX(550);
            alert.setY(300);
            alert.setHeaderText("");
            alert.setContentText("Are you sure you want to exit Kouvee PetShop ?");
            alert.showAndWait().ifPresent((btnType) -> {
                if (btnType == ButtonType.OK) {
                    System.exit(0);
                }
            });
        }

        if(me.getSource() == btnMenuUtama)
        {
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
            txtNama.setDisable(false);
            txtTelp.setDisable(false);
            txtAlamat.setDisable(false);

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
            txtNama.setDisable(false);
            txtTelp.setDisable(false);
            txtAlamat.setDisable(false);

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
            txtNama.setDisable(true);
            txtTelp.setDisable(true);
            txtAlamat.setDisable(true);

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

    //Search a Supplier
    @FXML
    void searchSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            //Get Employee Information
            Supplier spr = SupplierDAO.searchSupplier(txtCari.getText());

            //Populate Employee on TableView and Display on TextField
            populateAndShowSupplier(spr);

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Supplier information from DB" + e);
            DialogShowInfo("Error occurred while getting supplier information. Check your database connection");
            throw e;
        }
    }

    @FXML
    void searchSuppliers(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get all Supplier information
            ObservableList<Supplier> sprData = SupplierDAO.searchSuppliers();

            //Populate Suppliers on TableView
            populateSuppliers(sprData);
        } catch(SQLException e) {
            System.out.println("Error occurred while getting suppliers information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {

        sprId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        sprName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        sprAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        sprPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        searchSuppliers(getEvent);
    }

    //Populate Supplier
    @FXML
    private void populateSupplier(Supplier spr) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<Supplier> sprData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        sprData.add(spr);
        //Set items to the tableAll
        tableAll.setItems(sprData);
    }

    @FXML
    private void populateAndShowSupplier(Supplier spr) throws ClassNotFoundException {
        if(spr != null) {
            populateSupplier(spr);
        } else {
            System.out.println("This supplier doesn't exist");
            DialogShowInfo("Supplie not found with name " + txtCari.getText());
        }
    }

    @FXML
    private void populateSuppliers(ObservableList<Supplier> sprData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(sprData);
    }

    @FXML
    void deleteSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (txtID.getText().isEmpty()) {
            DialogShowInfo("Fields cannot be empty");
        } else if (!txtID.getText().matches("[0-9]+")) {
            DialogShowInfo("ID can only contain numbers.");
        } else {
            try {
                SupplierDAO.deleteSprWithId(txtID.getText());

            } catch (SQLException e) {
                System.out.println("Problem occurred while deleting supplier");
            }
        }
    }

    @FXML
    void updateSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (checkFields()) {
            DialogShowInfo("Fields cannot be empty");
        } else if (!txtID.getText().matches("[0-9]+")) {
            DialogShowInfo("ID can only contain numbers.");
        } else {
            try {
                SupplierDAO.updateEntries(returnID, txtID.getText(), txtNama.getText(), txtAlamat.getText(),
                        txtTelp.getText());

            } catch (SQLException e) {
                System.out.println("Problem occurred while updating supplier");
            }
        }
    }

    @FXML
    void insertSupplier(ActionEvent event) throws SQLException, ClassNotFoundException{

        if (checkFieldsNoID()) {
            DialogShowInfo("Fields cannot be empty");
        } else {
            try {
                SupplierDAO.insertSpr(returnID, txtNama.getText(), txtAlamat.getText(),
                        txtTelp.getText());

            } catch (SQLException e) {
                System.out.println("Problem occurred while inserting supplier");
            }
        }
    }

    @FXML
    void e1d42b(ActionEvent event) {

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
            Supplier supplier = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(supplier.getId()));
            txtNama.setText(supplier.getName());
            txtTelp.setText(supplier.getPhoneNumber());
            txtAlamat.setText(supplier.getAddress());
        }
    }

    @FXML
    private void clearFields(ActionEvent ae) {
        txtID.clear();
        txtNama.clear();
        txtTelp.clear();
        txtAlamat.clear();
    }

    private void DialogShowInfo(String text) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setX(550);
        info.setY(300);
        info.setHeaderText("");
        info.setContentText(text);
        info.showAndWait();
    }

    private boolean checkFields() {
        int counter = 0;
        boolean status = false;
        String[] text = {txtID.getText(), txtNama.getText(), txtAlamat.getText(), txtTelp.getText()};

        while (counter < text.length) {
            if (text[counter].isEmpty()) {
                status = true;
            }
            counter++;
        }

        return status;
    }

    private boolean checkFieldsNoID() {
        int counter = 0;
        boolean status = false;
        String[] text = {txtNama.getText(), txtAlamat.getText(), txtTelp.getText()};

        while (counter < text.length) {
            if (text[counter].isEmpty()) {
                status = true;
            }
            counter++;
        }

        return status;
    }
}
