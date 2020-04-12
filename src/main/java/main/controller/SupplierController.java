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
import main.dao.SupplierDAO;
import main.model.Supplier;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SupplierController implements Initializable {

    private static String returnID;
    private static String returnRole;

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
    private TableView<Supplier> tableAll;

    @FXML
    private TextField txtAlamat;

    @FXML
    private TableColumn<Supplier, String> sprName;

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

    @FXML
    public void handleButtonSupplier(MouseEvent me) {

        if (me.getSource() == btnSupplierKeluar) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

        if (me.getSource() == editLabel) {
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

        if (me.getSource() == deleteLabel) {
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
    void searchSupplier(ActionEvent event) throws SQLException {
        try {
            //Get Employee Information
            Supplier spr = SupplierDAO.searchSupplier(txtCari.getText());

            //Populate Employee on TableView and Display on TextField
            populateAndShowSupplier(spr);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Supplier information from DB" + e);
            throw e;
        }
    }

    @FXML
    void searchSuppliers(ActionEvent event) {
        loadAllData();
    }

    //Populate Supplier
    @FXML
    private void populateSupplier(Supplier spr) {

        //Declare an ObservableList for TableView
        ObservableList<Supplier> sprData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        sprData.add(spr);
        //Set items to the tableAll
        tableAll.setItems(sprData);
    }

    @FXML
    private void populateAndShowSupplier(Supplier spr) {
        if (spr != null) {
            populateSupplier(spr);
        } else {
            System.out.println("This supplier doesn't exist");
        }
    }

    @FXML
    private void populateSuppliers(ObservableList<Supplier> sprData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(sprData);
    }

    @FXML
    void deleteSupplier(ActionEvent event) {
        try {
            SupplierDAO.deleteSprWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting supplier");
        }
    }

    @FXML
    void updateSupplier(ActionEvent event) {
        String name = txtNama.getText().trim();
        String address = txtAlamat.getText().trim();
        String phone = txtTelp.getText().trim();
        String id = txtID.getText().trim();

        if (name.equals("") || address.equals("") || phone.equals("") || id.equals("")) {
            return;
        }

        Pattern pattern = Pattern.compile("\\d+");
        if (!pattern.matcher(phone).matches()) {
            return;
        }
        try {
            SupplierDAO.updateEntries(returnID, id, name, address,
                    phone);
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating supplier");
        }
    }

    @FXML
    void insertSupplier(ActionEvent event) throws ClassNotFoundException {
        String name = txtNama.getText().trim();
        String address = txtAlamat.getText().trim();
        String phone = txtTelp.getText().trim();

        if (name.equals("") || address.equals("") || phone.equals("")) {
            return;
        }

        Pattern pattern = Pattern.compile("\\d+");
        if (!pattern.matcher(phone).matches()) {
            return;
        }

        try {
            SupplierDAO.insertSpr(returnID, name, address,
                    phone);
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting supplier");
        }
    }

    @FXML
    void e1d42b(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sprId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        sprName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        sprAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        sprPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());

        loadAllData();
    }

    private void loadAllData() {
        //Get all Supplier information
        ObservableList<Supplier> sprData = null;
        try {
            sprData = SupplierDAO.searchSuppliers();
            populateSuppliers(sprData);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void clearFields() {
        txtAlamat.clear();
        txtCari.clear();
        txtID.clear();
        txtNama.clear();
        txtTelp.clear();

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
            Supplier supplier = tableAll.getSelectionModel().getSelectedItem();

            txtID.setText(Integer.toString(supplier.getId()));
            txtNama.setText(supplier.getName());
            txtTelp.setText(supplier.getPhoneNumber());
            txtAlamat.setText(supplier.getAddress());
        }
    }
}
