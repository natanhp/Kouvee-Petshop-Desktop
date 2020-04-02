package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.dao.EmployeeDAO;
import main.model.Employee;
import main.util.BCryptHash;
import main.util.FxDatePickerConverter;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

import static java.lang.Thread.sleep;

public class EmployeeController {

    private static String returnID;
    private static String returnRole;

    @FXML
    private TableColumn<Employee, Integer> empId;
    @FXML
    private TableColumn<Employee, String> empUname;
    @FXML
    private Button btnBantuan;
    @FXML
    private TextField txtNama;
    @FXML
    private Button btnTentang;
    @FXML
    private ImageView imgProfil;
    @FXML
    private TextField txtTglLahir;
    @FXML
    private DatePicker pickerDateBirth;
    @FXML
    private Button btnProduk;
    @FXML
    private TextField txtID;
    @FXML
    private Button btnUkuran;
    @FXML
    private Button btnCari;
    @FXML
    private Button btnBersih;
    @FXML
    private TableView<Employee> tableAll;
    @FXML
    private Button btnLayanan;
    @FXML
    private TableColumn<Employee, String> empName;
    @FXML
    private Button btnLihat;
    @FXML
    private Button hiddenActionButton;
    @FXML
    private TableColumn<Employee, Date> empDateBirth;
    @FXML
    private Button btnLogout;
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
    private Button btnCustomer;
    @FXML
    private TextField txtPawd;
    @FXML
    private Button btnLaporan;
    @FXML
    private ImageView imgLogo;
    @FXML
    private Button btnPegawai;
    @FXML
    private Button btnTransaksi;
    @FXML
    private Button btnEmployeeKeluar;
    @FXML
    private Button btnMenuUtama;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtUname;
    @FXML
    private TableColumn<Employee, String> empRole;
    @FXML
    private Button btnSupplier;
    @FXML
    private TableColumn<Employee, String> empAddress;
    @FXML
    private Button btnJenis;
    @FXML
    private TableColumn<Employee, String> empPhoneNumber;
    @FXML
    private TextField txtCari;


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
        if(me.getSource() == btnEmployeeKeluar)
            System.exit(0);

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

    //Search an Employee
    @FXML
    private void searchEmployee(ActionEvent ae) throws ClassNotFoundException, SQLException {

        try {
            //Get Employee Information
            Employee emp = EmployeeDAO.searchEmployee(txtCari.getText());

            //Populate Employee on TableView and Display on TextField
            populateAndShowEmployee(emp);

        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred while getting Employee information from DB" + e);
            throw e;
        }
    }

    @FXML
    private void searchEmployees(ActionEvent ae) throws ClassNotFoundException, SQLException {

        try {
            //Get all Employee information
            ObservableList<Employee> empData = EmployeeDAO.searchEmployees();

            //Populate Employees on TableView
            populateEmployees(empData);
        } catch(SQLException e) {
            System.out.println("Error occurred while getting employees information from DB " + e);
            throw e;
        }
    }

    @FXML
    private void initialize() {

        empId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        empName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        empDateBirth.setCellValueFactory(cellData -> cellData.getValue().dateBirthProperty());
        empAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        empPhoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        empRole.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        empUname.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        pickerDateBirth.setEditable(true);
        initializeDatePicker();
//        convertDatePicker();
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

//    private void convertDatePicker() {
//        String pattern = "dd/mm/yyyy";
//
//        FxDatePickerConverter converter = new FxDatePickerConverter(pattern);
//
//        pickerDateBirth.setConverter(converter);
//    }

    //Populate Employee
    @FXML
    private void populateEmployee(Employee emp) throws ClassNotFoundException {

        //Declare an ObservableList for TableView
        ObservableList<Employee> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the tableAll
        tableAll.setItems(empData);
    }

    @FXML
    private void populateAndShowEmployee(Employee emp) throws ClassNotFoundException {
        if(emp != null) {
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
    private void updateEmployee (ActionEvent ae) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {

        String generatedSecuredPasswordHash = BCryptHash.hashpw(txtPawd.getText(), BCryptHash.gensalt(10));

        try {
            EmployeeDAO.updateEntries(returnID, txtID.getText(), txtNama.getText(), pickerDateBirth.getValue().toString(), txtAlamat.getText(),
                    txtTelp.getText(), txtRole.getText(), txtUname.getText(), generatedSecuredPasswordHash);

        } catch (SQLException e) {
            System.out.println("Problem occurred while updating employee");
        }
    }

    @FXML
    private void insertEmployee (ActionEvent ae) throws ClassNotFoundException, SQLException {

        try {
            EmployeeDAO.insertEmp(returnID ,txtNama.getText(), pickerDateBirth.getValue().toString(), txtAlamat.getText(),
                    txtTelp.getText(), txtRole.getText(), txtUname.getText(), txtPawd.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while inserting employee");
        }
    }

    @FXML
    private void deleteEmployee (ActionEvent ae) throws ClassNotFoundException, SQLException {
        try {
            EmployeeDAO.deleteEmpWithId(txtID.getText());

        } catch (SQLException e) {
            System.out.println("Problem occurred while deleting employee");
        }
    }

}
