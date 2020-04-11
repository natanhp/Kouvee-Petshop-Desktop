package main.controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import main.dao.CustomerDAO;
import main.model.Customer;
import main.model.Employee;
import main.util.FxDatePickerConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private DatePicker pickerDateBirth;

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
    private Button btnPelangganKeluar;

    @FXML
    private Button btnLihat;

    @FXML
    private TableColumn<Customer, String> cusAddress;

    @FXML
    private TableColumn<Customer, Date> cusDateBirth;

    @FXML
    private TableColumn<Customer, String> cusPhoneNumber;

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

    @FXML
    private TextField txtCari;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public void handleButtonCustomer (MouseEvent me){
        if (me.getSource() == btnPelangganKeluar) {
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/MainMenuSecondary.fxml")));
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
            pickerDateBirth.setDisable(false);
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
            pickerDateBirth.setDisable(false);
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
            pickerDateBirth.setDisable(true);
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

        pickerDateBirth.setEditable(true);
        initializeDatePicker();

        FxDatePickerConverter converter = new FxDatePickerConverter("dd-MM-yyyy");

        pickerDateBirth.setConverter(converter);

        pickerDateBirth.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue){
                    pickerDateBirth.setValue(pickerDateBirth.getConverter().fromString(pickerDateBirth.getEditor().getText()));
                }
            }
        });
    }

    private void initializeDatePicker() {
        //Create a day cell factory
        Callback<DatePicker, DateCell> dayCellFactory =
                (final DatePicker datePicker) -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        //Must call super
                        super.updateItem(item, empty);

                        // Show Weekends in red color
                        DayOfWeek day = DayOfWeek.from(item);
                        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
                        {
                            this.setTextFill(Color.RED);
                        }
                        //Can only select until current date
                        if (item.isAfter(LocalDate.now()))
                        {
                            this.setDisable(true);
                        }
                    }
                };

        //Disable invalid date of births
        pickerDateBirth.setDayCellFactory(dayCellFactory);
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
            CustomerDAO.updateEntries(returnID, txtID.getText(), txtNama.getText(), pickerDateBirth.getValue().toString(), txtAlamat.getText(),
                    txtTelp.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating customer");
        }
    }

    @FXML
    private void insertCustomer (ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            CustomerDAO.insertCus(returnID, txtNama.getText(), pickerDateBirth.getValue().toString(), txtAlamat.getText(),
                    txtTelp.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting customer");
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
            Customer customer = tableAll.getSelectionModel().getSelectedItem();
            LocalDate lc = LocalDate.parse(customer.getDateBirth().toString());

            txtID.setText(Integer.toString(customer.getId()));
            txtNama.setText(customer.getName());
            pickerDateBirth.setValue(lc);
            txtTelp.setText(customer.getPhoneNumber());
            txtAlamat.setText(customer.getAddress());
        }
    }

    @FXML
    private void clearFields(ActionEvent ae) {
        txtID.clear();
        txtNama.clear();
        pickerDateBirth.setValue(null);
        txtTelp.clear();
        txtAlamat.clear();
    }

}
