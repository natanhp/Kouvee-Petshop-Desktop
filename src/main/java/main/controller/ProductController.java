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
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.dao.ProductDAO;
import main.model.Employee;
import main.model.Product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController {

    private static String returnID;
    private static String returnRole;
    private static String filePath;

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
    private Spinner<Double> spinHarga;

    @FXML
    private Spinner<Integer> spinMin;

    @FXML
    private Button btnCari;

    @FXML
    private Button btnBersih;

    @FXML
    private TableView<Product> tableAll;

    @FXML
    private TableColumn<Product, Integer> prPrice;

    @FXML
    private TableColumn<Product, String> prSatuan;

    @FXML
    private TableColumn<Product, byte[]> prGambar;

    @FXML
    private Button btnLihat;

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

    @FXML
    void handleButtonProduct(MouseEvent me) {
        if (me.getSource() == btnProdukKeluar) {
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
        if(me.getSource() == addLabel) {
            btnPerbarui.setDisable(true);
            btnTambah.setDisable(false);
            btnHapus.setDisable(true);
            txtID.setDisable(true);
            txtNamaProduk.setDisable(false);
            txtSatuan.setDisable(false);
            spinJumlah.setDisable(false);
            spinHarga.setDisable(false);
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

        if(me.getSource() == editLabel) {
            btnPerbarui.setDisable(false);
            btnTambah.setDisable(true);
            btnHapus.setDisable(true);
            txtID.setDisable(false);
            txtNamaProduk.setDisable(false);
            txtSatuan.setDisable(false);
            spinJumlah.setDisable(false);
            spinHarga.setDisable(false);
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

        if(me.getSource() == deleteLabel) {
            btnPerbarui.setDisable(true);
            btnTambah.setDisable(true);
            btnHapus.setDisable(false);
            txtID.setDisable(false);
            txtNamaProduk.setDisable(false);
            txtSatuan.setDisable(true);
            spinJumlah.setDisable(true);
            spinHarga.setDisable(true);
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
    void searchProduct(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            //Get Product Information
            Product pr = ProductDAO.searchProduct(txtCari.getText());

            //Populate Product on TableView and Display on TextField
            populateAndShowProduct(pr);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Product information from DB" + e);
            throw e;
        }
    }

    //Show all Products
    @FXML
    void searchProducts(ActionEvent event) throws SQLException, ClassNotFoundException{

        try {
            //Get all Products information
            ObservableList<Product> prData = ProductDAO.searchProducts();

            //Populate Products on TableView
            populateProducts(prData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting product information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void populateAndShowProduct (Product pr) throws ClassNotFoundException {
        if (pr != null) {
            populateProduct(pr);
        } else {
            System.out.println("This product doesn't exist");
        }
    }

    //Populate Products
    @FXML
    private void populateProduct (Product pr) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<Product> prData = FXCollections.observableArrayList();
        //Add product to the ObservableList
        prData.add(pr);
        //Set items to the tableAll
        tableAll.setItems(prData);
    }


    @FXML
    private void populateProducts (ObservableList <Product> prData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(prData);
    }

    @FXML
    void deleteProduct(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            ProductDAO.deletePrWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting product");
        }
    }

    @FXML
    void updateProduct(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            String qty = Integer.toString(spinJumlah.getValue().intValue());
            String min = Integer.toString(spinMin.getValue().intValue());
            String price = Integer.toString(spinHarga.getValue().intValue());

            ProductDAO.updateEntries(returnID, txtID.getText(), txtNamaProduk.getText(), txtSatuan.getText(), qty, price, min, filePath);

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating product");
        }
    }

    @FXML
    void insertProduct(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {

            String qty = Integer.toString(spinJumlah.getValue().intValue());
            String min = Integer.toString(spinMin.getValue().intValue());
            String price = Integer.toString(spinHarga.getValue().intValue());

            ProductDAO.insertPr(returnID, txtNamaProduk.getText(), qty, txtSatuan.getText(), price, min, filePath);


        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting product");
        }
    }

    @FXML
    void e1d42b(ActionEvent event) {

    }

    @FXML
    void openFileChooser(ActionEvent ae) throws IOException, ClassNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg"));
        File file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());

        if(file != null) {
            filePath = file.getPath();
        }
        else
        {
            System.out.println("Select image file");
        }
        Image image = new Image(file.toURI().toString());
        imagePreview.setImage(image);
    }

//    private byte[] convertFileContentIntoBlob(String filePath) throws IOException{
//
//        // create file object
//        File file = new File(filePath);
//        // initialize a byte array of size of the file
//        byte[] fileContent = new byte[(int) file.length()];
//        FileInputStream inputStream = new FileInputStream(file);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try {
//            for (int readNum; (readNum = inputStream.read(fileContent)) != -1;) {
//                //Writes to this byte array output stream
//                bos.write(fileContent, 0, readNum);
//                System.out.println("read " + readNum + " bytes,");
//            }
//            // create an input stream pointing to the file
//            // read the contents of file into byte array
////            inputStream.read(fileContent);
//        } catch (IOException e) {
//            throw new IOException("Unable to convert file to byte array. " +
//                    e.getMessage());
//        } finally {
//            // close input stream
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
//
//        byte[] bytes = bos.toByteArray();
//        return bytes;
//    }

//    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
//        WritableImage image = new WritableImage(width, height);
//        try {
//            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
//            BufferedImage read = ImageIO.read(bis);
//            image = SwingFXUtils.toFXImage(read, null);
//        } catch (IOException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return image;
//    }

    @FXML
    private void initialize() {

        int qty = 1;
        int minQty = 1;
        Double price = 1.0;

        SpinnerValueFactory<Integer> spinnerQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, qty);
        SpinnerValueFactory<Integer> spinnerMinQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, minQty);
        SpinnerValueFactory<Double> spinnerPrice = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 999999999, price);

        spinJumlah.setValueFactory(spinnerQuantity);
        spinJumlah.setEditable(true);
        spinJumlah.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                spinJumlah.increment(0); // won't change value, but will commit editor
            }
        });

        spinMin.setValueFactory(spinnerMinQuantity);
        spinMin.setEditable(true);
        spinMin.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                spinMin.increment(0); // won't change value, but will commit editor
            }
        });

        spinHarga.setValueFactory(spinnerPrice);
        spinHarga.setEditable(true);
        spinHarga.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue)
            {
                spinHarga.increment(0); // won't change value, but will commit editor
            }
        });

        prId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        prName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        prQty.setCellValueFactory(cellData -> cellData.getValue().productQuantityProperty().asObject());
        prSatuan.setCellValueFactory(cellData -> cellData.getValue().meassurementProperty());
        prPrice.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        prMinQty.setCellValueFactory(cellData -> cellData.getValue().minimumQuantityProperty().asObject());
    }

    @FXML
    void priceSpinner(MouseEvent me) {

    }

    @FXML
    void loadImage(ActionEvent ae) throws ClassNotFoundException {

    }

    @FXML
    private void selectedRow (MouseEvent me) throws ClassNotFoundException, SQLException, IOException {

        if(me.getClickCount() > 1)
        {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() throws IOException {

        ByteArrayInputStream bais = null;

        if(tableAll.getSelectionModel().getSelectedItem() != null) {
            Product product = tableAll.getSelectionModel().getSelectedItem();

            SpinnerValueFactory<Integer> spinnerQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, product.getProductQuantity());
            SpinnerValueFactory<Integer> spinnerMinQuantity = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 9999, product.getMinimumQuantity());
            SpinnerValueFactory<Double> spinnerPrice = new SpinnerValueFactory.DoubleSpinnerValueFactory(1, 999999999, product.getProductPrice());

            txtID.setText(Integer.toString(product.getId()));
            txtNamaProduk.setText(product.getProductName());
            txtSatuan.setText(product.getMeassurement());
            spinMin.setValueFactory(spinnerMinQuantity);
            spinHarga.setValueFactory(spinnerPrice);
            spinJumlah.setValueFactory(spinnerQuantity);

            imagePreviewDB.setImage(null);

            try {
                bais = new ByteArrayInputStream(product.getImage());
                bais.close();

                // read till the end of the stream
                while(bais.available() >0) {

                    // convert byte to character
//                    char c = (char)bais.read();

                    // print number of bytes available
//                    System.out.print("available byte(s) : "+ count);

                    // print characters read form the byte array
//                    System.out.println(" & byte read : "+c);

                    Image image = new Image(bais);
                    imagePreviewDB.setImage(image);
                }


            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                System.out.println("Error occurred when opening the image / no image available " + ex);
                labelImageError.setText("No Image Available");

            } finally {
                if(bais != null) {
                    bais.close();
                }

            }


        }
    }

}