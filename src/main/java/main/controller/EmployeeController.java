package main.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
import javafx.util.Callback;
import main.dao.EmployeeDAO;
import main.model.Employee;
import main.util.BCryptHash;
import main.util.FxDatePickerConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class EmployeeController implements Initializable {

    private static String returnID;
    private static String returnRole;

    @FXML
    private TableColumn<Employee, Integer> empId;
    @FXML
    private TableColumn<Employee, String> empUname;
    @FXML
    private TextField txtNama;
    @FXML
    private DatePicker pickerDateBirth;
    @FXML
    private TextField txtID;

    @FXML
    private TableView<Employee> tableAll;
    @FXML
    private TableColumn<Employee, String> empName;

    @FXML
    private TableColumn<Employee, Date> empDateBirth;
    @FXML
    private Button btnHapus;
    @FXML
    private TextField txtTelp;
    @FXML
    private TextField txtRole;
    @FXML
    private Button btnPerbarui;
    @FXML
    private Button btnTambah;
    @FXML
    private TextField txtPawd;

    @FXML
    private Button btnPegawaiKeluar;
    @FXML
    private Button btnMenuUtama;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtUname;
    @FXML
    private TableColumn<Employee, String> empRole;
    @FXML
    private TableColumn<Employee, String> empAddress;
    @FXML
    private TableColumn<Employee, String> empPhoneNumber;
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


    @FXML
    void c4c4c4cb(ActionEvent event) {

    }

    public static void getUserLogin(String loginID) {

        returnID = loginID;
    }

    public static void getRoleLogin(String loginRole) {

        returnRole = loginRole;
    }

    public void handleButtonEmployee(MouseEvent me) {
        if (me.getSource() == btnPegawaiKeluar) {
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
            pickerDateBirth.setDisable(false);
            txtTelp.setDisable(false);
            txtAlamat.setDisable(false);
            txtRole.setDisable(false);
            txtUname.setDisable(false);
            txtPawd.setDisable(false);

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
            pickerDateBirth.setDisable(false);
            txtTelp.setDisable(false);
            txtAlamat.setDisable(false);
            txtRole.setDisable(false);
            txtUname.setDisable(false);
            txtPawd.setDisable(false);

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
            pickerDateBirth.setDisable(true);
            txtTelp.setDisable(true);
            txtAlamat.setDisable(true);
            txtRole.setDisable(true);
            txtUname.setDisable(true);
            txtPawd.setDisable(true);


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

    //Search an Employee
    @FXML
    private void searchEmployee(ActionEvent ae) throws ClassNotFoundException, SQLException {

        try {
            //Get Employee Information
            Employee emp = EmployeeDAO.searchEmployee(txtCari.getText());

            //Populate Employee on TableView and Display on TextField
            populateAndShowEmployee(emp);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Employee information from DB" + e);
            throw e;
        }
    }

    @FXML
    private void searchEmployees(ActionEvent ae) {
        loadAllData();
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
                        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                            this.setTextFill(Color.RED);
                        }
                        //Can only select until current date
                        if (item.isAfter(LocalDate.now())) {
                            this.setDisable(true);
                        }
                    }
                };

        //Disable invalid date of births
        pickerDateBirth.setDayCellFactory(dayCellFactory);
    }

    //Populate Employee
    @FXML
    private void populateEmployee(Employee emp) {

        //Declare an ObservableList for TableView
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the tableAll
        tableAll.setItems(empData);
    }

    @FXML
    private void populateAndShowEmployee(Employee emp) {
        if (emp != null) {
            populateEmployee(emp);
        } else {
            System.out.println("This employee doesn't exist");
        }
    }

    @FXML
    private void populateEmployees(ObservableList<Employee> empData) throws ClassNotFoundException {

        //Set items to the tableAll
        tableAll.setItems(empData);
    }

    @FXML
    private void updateEmployee(ActionEvent ae) {

        String name = txtNama.getText().trim();
        String birthDate = pickerDateBirth.getValue().toString().trim();
        String address = txtAlamat.getText().trim();
        String phone = txtTelp.getText().trim();
        String role = txtRole.getText().trim();
        String userName = txtUname.getText().trim();
        String password = txtPawd.getText().trim();
        String id = txtID.getText().trim();

        if (name.equals("") || birthDate.equals("") || address.equals("") || phone.equals("") ||
                userName.equals("") || password.equals("") || id.equals("")) {
            return;
        }

        Pattern pattern = Pattern.compile("\\d+");
        if (!pattern.matcher(phone).matches()) {
            return;
        }

        if (!role.equals("Owner") && !role.equals("CS") && !role.equals("Kasir")) {
            return;
        }

        String generatedSecuredPasswordHash;

        if (password.equalsIgnoreCase("No change")) {
            try {
                EmployeeDAO.updateEntries(returnID, id, name, birthDate, address,
                        phone, role, userName, password);

            } catch (SQLException e) {
                System.out.println("Problem occurred while updating employee");
            }
        } else {
            generatedSecuredPasswordHash = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(10, password.toCharArray());
            BCrypt.Result result1 = BCrypt.verifyer().verify(password.toCharArray(), generatedSecuredPasswordHash);

            try {
                EmployeeDAO.updateEntries(returnID, id, name, birthDate, address,
                        phone, role, userName, generatedSecuredPasswordHash);

            } catch (SQLException e) {
                System.out.println("Problem occurred while updating employee");
            }
        }

        loadAllData();
    }

    @FXML
    private void insertEmployee(ActionEvent ae) {

        String name = txtNama.getText().trim();
        String birthDate = pickerDateBirth.getValue().toString().trim();
        String address = txtAlamat.getText().toString().trim();
        String phone = txtTelp.getText().toString().trim();
        String role = txtRole.getText().toString().trim();
        String userName = txtUname.getText().toString().trim();
        String password = txtPawd.getText().toString().trim();

        if (name.equals("") || birthDate.equals("") || address.equals("") || phone.equals("") ||
                userName.equals("") || password.equals("")) {
            return;
        }

        Pattern pattern = Pattern.compile("\\d+");
        if (!pattern.matcher(phone).matches()) {
            return;
        }

        if (!role.equals("Owner") && !role.equals("CS") && !role.equals("Kasir")) {
            return;
        }

        String generatedSecuredPasswordHash;

        generatedSecuredPasswordHash = BCryptHash.hashpw(password, BCryptHash.gensalt(10));
        try {
            EmployeeDAO.insertEmp(returnID, name, birthDate, address,
                    phone, role, userName, generatedSecuredPasswordHash);

            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting employee");
        }
    }

    @FXML
    private void deleteEmployee(ActionEvent ae) {
        try {
            EmployeeDAO.softDeleteEmpWithId(returnID, txtID.getText());

            loadAllData();
        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting employee");
        }
    }

    @FXML
    private void selectedRow(MouseEvent me) {

        if (me.getClickCount() > 1) {
            editWithSelectedRow();
        }
    }

    private void editWithSelectedRow() {


        if (tableAll.getSelectionModel().getSelectedItem() != null) {
            Employee employee = tableAll.getSelectionModel().getSelectedItem();
            LocalDate lc = LocalDate.parse(employee.getDateBirth().toString());

            txtID.setText(Integer.toString(employee.getId()));
            txtNama.setText(employee.getName());
            pickerDateBirth.setValue(lc);
            txtTelp.setText(employee.getPhoneNumber());
            txtAlamat.setText(employee.getAddress());
            txtRole.setText(employee.getRole());
            txtUname.setText(employee.getUsername());
            txtPawd.setText("No change");
        }
    }

    @FXML
    private void clearFields(ActionEvent ae) {
        txtID.clear();
        txtNama.clear();
        pickerDateBirth.setValue(null);
        txtTelp.clear();
        txtAlamat.clear();
        txtRole.clear();
        txtUname.clear();
        txtPawd.clear();
        txtCari.clear();

        loadAllData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        empName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        empDateBirth.setCellValueFactory(cellData -> cellData.getValue().dateBirthProperty());
        empAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        empPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        empRole.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        empUname.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        pickerDateBirth.setEditable(true);
        initializeDatePicker();

        FxDatePickerConverter converter = new FxDatePickerConverter();

        pickerDateBirth.setConverter(converter);

        pickerDateBirth.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                pickerDateBirth.setValue(pickerDateBirth.getConverter().fromString(pickerDateBirth.getEditor().getText()));
            }
        });

        loadAllData();
    }

    private void loadAllData() {
        //Get all Employee information
        ObservableList<Employee> empData = null;
        try {
            empData = EmployeeDAO.searchEmployees();
            populateEmployees(empData);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
