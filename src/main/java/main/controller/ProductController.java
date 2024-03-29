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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.dao.ProductDAO;
import main.model.Product;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController implements Initializable {

    private static String returnID;
    private static String returnRole;
    private static String filePath;
    private static ActionEvent getEvent;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnProdukKeluar;

    @FXML
    private TableColumn<Product, String> prName;

    @FXML
    private TableColumn<Product, Integer> prId;

    @FXML
    private TextField txtNamaProduk;

    @FXML
    private TableColumn<Product, Integer> prQty;

    @FXML
    private Button btnPerbarui;

    @FXML
    private Button btnTambah;

    @FXML
    private TableColumn<Product, Integer> prMinQty;

    @FXML
    private Button btnMenuUtama;

    @FXML
    private Button btnOpen;

    @FXML
    private TextField txtID;

    @FXML
    private Spinner<Integer> spinJumlah;

    @FXML
    private TextField txtPrice;

    @FXML
    private Spinner<Integer> spinMin;

    @FXML
    private TableView<Product> tableAll;

    @FXML
    private TableColumn<Product, Integer> prPrice;

    @FXML
    private TableColumn<Product, String> prSatuan;

    @FXML
    private ImageView imagePreview;

    @FXML
    private TextField txtSatuan;

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

    @FXML
    private ImageView imagePreviewDB;

    @FXML
    private Label labelImageError;

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
            txtNamaProduk.setDisable(false);
            txtSatuan.setDisable(false);
            spinJumlah.setDisable(false);
            txtPrice.setDisable(false);
            spinMin.setDisable(false);

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
            txtNamaProduk.setDisable(false);
            txtSatuan.setDisable(false);
            spinJumlah.setDisable(false);
            txtPrice.setDisable(false);
            spinMin.setDisable(false);

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
            txtNamaProduk.setDisable(false);
            txtSatuan.setDisable(true);
            spinJumlah.setDisable(true);
            txtPrice.setDisable(true);
            spinMin.setDisable(true);

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

    //Search Product
    @FXML
    void searchProduct(ActionEvent event) throws SQLException {

        try {
            //Get Product Information
            populateProducts(ProductDAO.searchProduct(txtCari.getText()));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Product information from DB" + e);
            DialogShowInfo("Error occurred while getting product information from database. Check your database connection");
            throw e;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Show all Products
    @FXML
    void searchProducts(ActionEvent event) {
        loadAllData();
    }

    @FXML
    private void populateAndShowProduct(Product pr) {
        if (pr != null) {
            populateProduct(pr);
        } else {
            System.out.println("This product doesn't exist");
        }
    }

    //Populate Products
    @FXML
    private void populateProduct(Product pr) {

        //Declare an ObservableList for TableView
        ObservableList<Product> prData = FXCollections.observableArrayList();
        //Add product to the ObservableList
        prData.add(pr);
        //Set items to the tableAll
        tableAll.setItems(prData);
    }


    @FXML
    private void populateProducts(ObservableList<Product> prData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(prData);
    }

    @FXML
    void deleteProduct(ActionEvent event) {

        try {
            ProductDAO.softDeletePrWithId(returnID, txtID.getText());
            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting product");
        }
    }

    @FXML
    void updateProduct(ActionEvent event) {
        int quantity = spinJumlah.getValue();
        int minQty = spinMin.getValue();
        String priceText = txtPrice.getText().trim();
        String productName = txtNamaProduk.getText().trim();
        String metric = txtSatuan.getText().trim();


        if (productName.equals("") || metric.equals("") || priceText.equals("")) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }

        double priceValue = Double.parseDouble(priceText);

        if (quantity <= 0 || minQty <= 0 || priceValue <= 0) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }

        String qty = Integer.toString(quantity);
        String min = Integer.toString(minQty);
        String price = Double.toString(priceValue);

        try {

            if (filePath == null) {
                ProductDAO.updateEntriesNoImage(returnID, txtID.getText(), productName, metric, qty, price, min);
            } else {
                ProductDAO.updateEntries(returnID, txtID.getText(), productName, metric, qty, price, min, filePath);
            }

            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while updating product");
        }
    }

    @FXML
    void insertProduct(ActionEvent event) {
        int quantity = spinJumlah.getValue();
        int minQty = spinMin.getValue();
        String priceText = txtPrice.getText().trim();
        String productName = txtNamaProduk.getText().trim();
        String metric = txtSatuan.getText().trim();


        if (productName.equals("") || metric.equals("") || priceText.equals("")) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }

        double priceValue = Double.parseDouble(priceText);

        if (quantity <= 0 || minQty <= 0 || priceValue <= 0) {
            DialogShowInfo("Fields cannot be empty");
            return;
        }
        String qty = Integer.toString(quantity);
        String min = Integer.toString(minQty);
        String price = Double.toString(priceValue);

        try {
            ProductDAO.insertPr(returnID, productName, qty, metric, price, min, filePath);
            loadAllData();
            filePath = null;
        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting product");
        }
    }

    @FXML
    void e1d42b(ActionEvent event) {

    }

    @FXML
    void openFileChooser(ActionEvent ae) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpeg", "*.jpg"));
        File file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());

        if (file != null) {
            filePath = file.getPath();
        } else {
            System.out.println("Select image file");
        }
        Image image = new Image(file.toURI().toString());
        imagePreview.setImage(image);
    }

    @FXML
    void priceSpinner(MouseEvent me) {

    }

    @FXML
    void loadImage(ActionEvent ae) throws ClassNotFoundException {

    }

    @FXML
    private void selectedRow(MouseEvent me) throws ClassNotFoundException, SQLException, IOException {

        if (me.getClickCount() > 1) {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() throws IOException {

        ByteArrayInputStream bais = null;

        if (tableAll.getSelectionModel().getSelectedItem() != null) {
            Product product = tableAll.getSelectionModel().getSelectedItem();

            SpinnerValueFactory<Integer> spinnerQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, product.getProductQuantity());
            SpinnerValueFactory<Integer> spinnerMinQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, product.getMinimumQuantity());

            txtID.setText(Integer.toString(product.getId()));
            txtNamaProduk.setText(product.getProductName());
            txtSatuan.setText(product.getMeassurement());
            spinMin.setValueFactory(spinnerMinQuantity);
            spinJumlah.setValueFactory(spinnerQuantity);
            txtPrice.setText(Double.toString(product.getProductPrice()));
            imagePreviewDB.setImage(null);

            try {
                bais = new ByteArrayInputStream(product.getImage());
                bais.close();

                while (bais.available() > 0) {
                    Image image = new Image(bais);
                    imagePreviewDB.setImage(image);
                }


            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                System.out.println("Error occurred when opening the image / no image available " + ex);
                labelImageError.setText("No Image Available");

            } finally {
                if (bais != null) {
                    bais.close();
                }

            }


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int qty = 0;
        int minQty = 0;
        Double price = 0.0;

        SpinnerValueFactory<Integer> spinnerQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, qty);
        SpinnerValueFactory<Integer> spinnerMinQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, minQty);

        spinJumlah.setValueFactory(spinnerQuantity);
        spinJumlah.setEditable(true);
        spinJumlah.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                spinJumlah.increment(0); // won't change value, but will commit editor
            }
        });

        spinMin.setValueFactory(spinnerMinQuantity);
        spinMin.setEditable(true);
        spinMin.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                spinMin.increment(0); // won't change value, but will commit editor
            }
        });

        prId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        prName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        prQty.setCellValueFactory(cellData -> cellData.getValue().productQuantityProperty().asObject());
        prSatuan.setCellValueFactory(cellData -> cellData.getValue().meassurementProperty());
        prPrice.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        prMinQty.setCellValueFactory(cellData -> cellData.getValue().minimumQuantityProperty().asObject());

        loadAllData();
    }

    private void loadAllData() {
        //Get all Products information
        ObservableList<Product> prData = null;
        try {
            prData = ProductDAO.searchProducts();
            //Populate Products on TableView
            populateProducts(prData);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void clearFields() {
        txtID.clear();
        txtNamaProduk.clear();
        txtCari.clear();
        txtSatuan.clear();
        filePath = null;
        imagePreviewDB.setImage(null);
        imagePreview.setImage(null);

        SpinnerValueFactory<Integer> productQtySpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, 0);
        spinJumlah.setValueFactory(productQtySpinnerFactory);

        SpinnerValueFactory<Integer> minimumQtySpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 999999999, 0);
        spinMin.setValueFactory(minimumQtySpinnerFactory);

        loadAllData();
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
