package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.dao.EmployeeDAO;
import main.model.Employee;
import main.util.DBUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController implements Initializable {

    private String loginID;
    private String loginRole;
    private String loginUname;
    @FXML
    private Button btnExit;

    @FXML
    private Circle imgLogo;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUname;

    @FXML
    private TextField txtPawd;

    @FXML
    private Label lblErrors;

    @FXML
    void bcbcbc(ActionEvent event) {

    }

    Connection conn = DBUtil.conDB();
    EmployeeDAO employeeDAO = new EmployeeDAO();


    public void handleButtonAction(MouseEvent me) {
        if (me.getSource() == btnExit) {
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

        if (me.getSource() == btnLogin) {
            //login here
            if (loginAction().equals("Admin") || loginAction().equals("Owner")) {
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
            } else if (loginAction().equals("CS") || loginAction().equals("Customer Service")) {
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
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (conn == null) {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Restart the application");
        } else {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is Up : Good to go");
        }
    }

    @FXML
    private String loginAction() {

        Scanner scanner = new Scanner((System.in));
        String status = "Success";

        String username = txtUname.getText();
        String password = txtPawd.getText();

        if (username.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            Employee employee = employeeDAO.login(username, password);
            if (employee != null) {
                loginID = String.valueOf(employee.getId());
                loginRole = employee.getRole();
                loginUname = employee.getName();

                setAllUserLogin();
                setLblError(Color.GREEN, "Login Successful");
                status = loginRole;
            } else {
                setLblError(Color.TOMATO, "Username/password doesn't exist. Try again.");
                status = "Error";
            }
        }
        return status;
    }

    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    public String getUserLogin() {
        return loginID;
    }

    public String getUserRole() {
        return loginRole;
    }

    public String getUsername() {
        return loginUname;
    }

    public void setAllUserLogin() {

        MainMenuController.getUnameLogin(getUsername());
        MainMenuSecondaryController.getUnameLogin(getUsername());

        EmployeeController.getUserLogin(getUserLogin());
        EmployeeController.getRoleLogin(getUserRole());

        CustomerSecondaryController.getUserLogin(getUserLogin());
        CustomerSecondaryController.getRoleLogin(getUserRole());

        SupplierController.getUserLogin(getUserLogin());
        SupplierController.getRoleLogin(getUserRole());

        PetSizeController.getUserLogin(getUserLogin());
        PetSizeController.getRoleLogin(getUserRole());

        PetTypeController.getUserLogin(getUserLogin());
        PetTypeController.getRoleLogin(getUserRole());

        PetController.getUserLogin(getUserLogin());
        PetController.getRoleLogin(getUserRole());

        ServiceController.getUserLogin(getUserLogin());
        ServiceController.getRoleLogin(getUserRole());

        ProductController.getUserLogin(getUserLogin());
        ProductController.getRoleLogin(getUserRole());
    }
}
