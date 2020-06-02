package main.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.util.StringConverter;
import main.dao.*;
import main.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OrderProductController implements Initializable {


    private static String returnID;
    private static String returnRole;

    @FXML
    private Button btnProdukKeluar;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private Button btnPeliharaan;

    @FXML
    private TableView<Product> tableAll;

    @FXML
    private TableColumn<Product, Integer> productId;

    @FXML
    private TableColumn<Product, String> productName;

    @FXML
    private TableColumn<Product, String> productType;

    @FXML
    private TableColumn<Product, Integer> productPrice;

    @FXML
    private TableView<ProductTransactionDetail> tableOrder;

    @FXML
    private TableColumn<ProductTransactionDetail, String> orderId;

    @FXML
    private TableColumn<ProductTransactionDetail, String> orderEmployee;

    @FXML
    private TableColumn<ProductTransactionDetail, String> orderCustomer;

    @FXML
    private TableColumn<ProductTransactionDetail, String> orderProduk;

    @FXML
    private TableColumn<ProductTransactionDetail, Double> orderTotal;

    @FXML
    private ImageView addLogo;

    @FXML
    private Label addLabel;

    @FXML
    private ImageView editLogo;

    @FXML
    private Label editLabel;

    @FXML
    private ImageView deleteLogo;

    @FXML
    private ImageView addLogoService;

    @FXML
    private Label deleteLabel;

    @FXML
    private Label addLabelService;

    @FXML
    private TextField txtOrder;

    @FXML
    private TextField txtJumlah;

    @FXML
    private ComboBox<Customer> comboCustomer;

    @FXML
    private ComboBox<Product> comboProduct;

    @FXML
    private TextField txtCari;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnLihat;

    @FXML
    private Button btnBersih;

    @FXML
    private Button btnPesan;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnTambah;

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    @FXML
    void addProductOrder(ActionEvent event) {
        try {
            String produk = Integer.toString(comboProduct.getValue().getId());

            ProductTransactionDetailDAO.insertPtd(returnID, produk, txtOrder.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting pettype");
        }
    }

    @FXML
    void clearFields(ActionEvent ae) {

        txtOrder.clear();
        comboCustomer.getEditor().clear();
        comboProduct.getEditor().clear();
        txtCari.clear();

        loadAllData();
    }

    @FXML
    void deleteOrder(ActionEvent event) {
        try {
            ProductTransactionDAO.deleteTransactionWithId(txtOrder.getText());
            ProductTransactionDetailDAO.deleteTranWithId(txtOrder.getText());

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem occurred while deleting Service Order");
        }
    }

    @FXML
    void handleButtonProduct(MouseEvent me) {
        if (me.getSource() == btnProdukKeluar) {
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
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/MainMenuSecondary.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    void orderProduct(ActionEvent event) {
        try {

            String customer = Integer.toString(comboCustomer.getValue().getId());
            String produk = Integer.toString(comboProduct.getValue().getId());

            int jumlah = Integer.parseInt(txtJumlah.getText());

            ProductTransactionDAO.insertTransaction(returnID, txtOrder.getText(),
                    customer, returnID, jumlah,0);

            ProductTransactionDetailDAO.insertPtd(returnID, produk, txtOrder.getText());
            ProductTransactionDAO.updateEntries(returnID, txtOrder.getText(), produk, returnID, customer, 0);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem occurred while inserting pettype");
        }
    }

    @FXML
    void searchProduct(ActionEvent event) {

    }

    @FXML
    void searchProducts(ActionEvent event) {
        loadAllData();
    }

    @FXML
    void selectPet(MouseEvent event) throws SQLException {
        comboCustomer.setMaxHeight(20);

        try {
            //Try getting all the PetTypes information
            ObservableList<Customer> petData = CustomerDAO.searchCustomers();

            //Populate Pets on ComboBox
            populatePetComboBox(petData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting all pets information from DB " + e);
            throw e;
        }
    }

    @FXML
    void selectService(MouseEvent event) throws SQLException, ClassNotFoundException {
        comboProduct.setMaxHeight(20);

        try {
            //Try getting all the PetTypes and PetSizes information
            ObservableList<Product> serviceData = ProductDAO.searchProducts();

            //Populate PetTypes and PetSizes on ComboBox
            populateProductDetailComboBox(serviceData);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error occurred while getting all petsize information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void populatePetComboBox(ObservableList<Customer> petData) throws SQLException {

        //Set items to the comboBox
        if (comboCustomer == null) {
            return;
        }

        comboCustomer.setItems(petData);
        comboCustomer.setConverter(new StringConverter<Customer>() {

            @Override
            public String toString(Customer object) {
                if (object != null) {
                    return object.getName();
                }

                return "";
            }

            public int getObjectID(Customer object) {
                return object.getId();
            }

            @Override
            public Customer fromString(String string) {
                // TODO Auto-generated method stub
                return null;
            }
        });

        comboCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {

            }
        });

    }

    @FXML
    private void populateProductDetailComboBox(ObservableList<Product> serviceData) throws SQLException {

        //Set items to the comboBox
        if (comboProduct == null) {
            return;
        }

        comboProduct.setItems(serviceData);
        comboProduct.setConverter(new StringConverter<Product>() {

            @Override
            public String toString(Product object) {
                if (object != null) {
                    return object.getProductName()
                            ;
                }

                return "";
            }

            public int getObjectID(Product object) {
                return object.getId();
            }

            @Override
            public Product fromString(String string) {
                // TODO Auto-generated method stub
                return null;
            }
        });
    }

    @FXML
    void selectedRowOrder(MouseEvent me) throws SQLException, ClassNotFoundException {
        if (me.getClickCount() > 1) {
            editWithSelectedRowOrder();
        }
    }

    @FXML
    void selectedRowService(MouseEvent event) {

    }

    @FXML
    private void switchOperations(MouseEvent me) {
        addLabel.setTextFill(Color.WHITE);
        if(me.getSource() == addLabel) {
            btnPesan.setDisable(false);
            btnPerbarui.setDisable(true);
            btnHapus.setDisable(true);
            btnTambah.setDisable(true);
            txtOrder.setDisable(false);
            comboCustomer.setDisable(false);
            comboProduct.setDisable(false);

            addLabel.setTextFill(Color.WHITE);
            addLabelService.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_white_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if(me.getSource() == addLabelService) {
            btnPesan.setDisable(true);
            btnPerbarui.setDisable(true);
            btnHapus.setDisable(true);
            btnTambah.setDisable(false);
            txtOrder.setDisable(false);
            comboCustomer.setDisable(true);
            comboProduct.setDisable(false);

            addLabel.setTextFill(Color.BLACK);
            addLabelService.setTextFill(Color.WHITE);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_white_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if(me.getSource() == editLabel) {
            btnPesan.setDisable(true);
            btnPerbarui.setDisable(false);
            btnHapus.setDisable(true);
            btnTambah.setDisable(true);
            txtOrder.setDisable(false);
            comboCustomer.setDisable(false);
            comboProduct.setDisable(false);

            addLabel.setTextFill(Color.BLACK);
            addLabelService.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.WHITE);
            deleteLabel.setTextFill(Color.BLACK);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_white_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_black_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }

        if(me.getSource() == deleteLabel) {
            btnPesan.setDisable(true);
            btnPerbarui.setDisable(true);
            btnHapus.setDisable(false);
            btnTambah.setDisable(true);
            txtOrder.setDisable(false);
            comboCustomer.setDisable(true);
            comboProduct.setDisable(true);

            addLabel.setTextFill(Color.BLACK);
            addLabelService.setTextFill(Color.BLACK);
            editLabel.setTextFill(Color.BLACK);
            deleteLabel.setTextFill(Color.WHITE);

            addLogo.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            addLogoService.setImage(new Image("icons/baseline_add_circle_black_18dp.png"));
            editLogo.setImage(new Image("icons/baseline_edit_black_18dp.png"));
            deleteLogo.setImage(new Image("icons/baseline_delete_white_18dp.png"));
            addLogo.getImage();
            addLogoService.getImage();
            editLogo.getImage();
            deleteLogo.getImage();
        }
    }

    //Populate Products
    @FXML
    private void populateProduct(Product p) {

        //Declare an ObservableList for TableView
        ObservableList<Product> sdData = FXCollections.observableArrayList();
        //Add product detail to the ObservableList
        sdData.add(p);
        //Set items to the tableAll
        tableAll.setItems(sdData);
    }


    @FXML
    private void populateProducts(ObservableList<Product> sdData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(sdData);
    }

    @FXML
    private void populateProductTransactionDetails(ObservableList<ProductTransactionDetail> stdData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableOrder.setItems(stdData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        productName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productType.setCellValueFactory(cellData -> cellData.getValue().meassurementProperty());
        productPrice.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());

        orderId.setCellValueFactory(cellData -> cellData.getValue().productTransaction_IdProperty());
        orderEmployee.setCellValueFactory(cellData -> cellData.getValue().employees_IdProperty());
        orderCustomer.setCellValueFactory(cellData -> cellData.getValue().customers_IdProperty());
        orderProduk.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());

        orderTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        ObservableList typeList = FXCollections.observableArrayList();
        comboCustomer.getItems().clear();
        comboProduct.getItems().clear();
        comboCustomer.setItems(typeList);
        comboProduct.setItems(typeList);

        loadAllData();
    }

    private void loadAllData() {
        //Get all PetType information
        ObservableList<Product> sdData = null;
        ObservableList<ProductTransactionDetail> stdData = null;
        try {
            sdData = ProductDAO.searchProducts();
            stdData = ProductTransactionDetailDAO.searchProductTransactionDetails();
            //Populate ServiceDetails on TableView
            populateProducts(sdData);
            //Populate ServiceTransactionDetails on TableView
            populateProductTransactionDetails(stdData);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void updateOrder(ActionEvent event) {

        String orderID = txtOrder.getText().trim();
        if (comboCustomer.getValue() == null || comboProduct.getValue() == null || orderID.equals("")) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }

        try {
            String customer = Integer.toString(comboCustomer.getValue().getId());
            String produk = Integer.toString(comboProduct.getValue().getId());

            ProductTransactionDetail std = ProductTransactionDetailDAO.searchID(produk, txtOrder.getText());

            String stdID = Integer.toString(std.getId());

            ProductTransactionDetailDAO.updateEntries(returnID, stdID, produk, txtOrder.getText());
            ProductTransactionDAO.updateEntries(returnID, txtOrder.getText(), produk, returnID, customer, 0);

            loadAllData();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Problem occurred while updating order");
        }
    }

    private void editWithSelectedRowOrder() throws SQLException, ClassNotFoundException {
        if (tableOrder.getSelectionModel().getSelectedItem() != null) {
            ProductTransactionDetail std = tableOrder.getSelectionModel().getSelectedItem();

            ObservableList<Customer> petData = CustomerDAO.searchCustomers();
            populatePetComboBox(petData);
            ObservableList<Product> serviceData = ProductDAO.searchProducts();
            populateProductDetailComboBox(serviceData);

            txtOrder.setText(std.getProductTransaction_Id());
            comboProduct.getItems();
            comboCustomer.getItems();

            for (Customer customer : comboCustomer.getItems()) {
                if (customer.getName().equals(std.getCustomers_Id())) {
                    comboCustomer.getSelectionModel().select(customer);
                }
            }

            for (Product product : comboProduct.getItems()) {
                if (product.getProductName().equals(std.getProducts_Id())) {
                    comboProduct.getSelectionModel().select(product);
                }
            }
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
