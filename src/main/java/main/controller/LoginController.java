package main.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.model.Employee;
import main.model.PetSize;
import main.util.BCryptHash;
import main.util.DBUtil;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    public void handleButtonAction(MouseEvent me) {
        if(me.getSource() == btnExit) {
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

        if(me.getSource() == btnLogin)
        {
            //login here
            if(loginAction().equals("Admin"))
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
            else if(loginAction().equals("Kasir") || loginAction().equals("Cashier"))
            {
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
//            else if(loginAction().equals("CS")){
//                lblErrors.setTextFill(Color.TOMATO);
//                lblErrors.setText("Access Denied !");
//            }

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

    public LoginController() {
        conn = DBUtil.conDB();
    }

    @FXML
    private String loginAction() {

        Scanner scanner = new Scanner((System.in));
        String status = "Success";

        String username = txtUname.getText();
        String password = txtPawd.getText();
        String generatedSecuredPasswordHash = BCryptHash.hashpw(password, BCryptHash.gensalt(10));
        String temp = null;
        String tempPassword = null;
        boolean matched = false;

        //Query
//        String query = "SELECT id, role FROM employees WHERE username = ? and password = ?;";
        String queryCheck = "SELECT id, name, role, password FROM employees WHERE username = ?;";

        if(username.isEmpty() || password.isEmpty()) {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        } else {
            try {
                preparedStatement = conn.prepareStatement(queryCheck);
                preparedStatement.setString(1, username);
//                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next() == false)
                {
                    setLblError(Color.TOMATO, "Username/password doesn't exist. Try again.");
                    status = "Error";
                }
                else if(resultSet.first() == true)
                {
                    do {
                        temp = resultSet.getString("password");
                        System.out.println(temp);
                        tempPassword = temp;
                    } while (resultSet.next());

                    matched = BCryptHash.checkpw(password, tempPassword);

                    if(matched == true) {
                        resultSet.first();
                        do {
                            temp = resultSet.getString("id");
                            System.out.println(temp);
                            loginID = temp;
                            temp = resultSet.getString("role");
                            System.out.println(temp);
                            loginRole = temp;
                            temp = resultSet.getString("name");
                            loginUname = temp;
                            System.out.println(temp);
                        } while (resultSet.next());

                        setAllUserLogin();
                        setLblError(Color.GREEN, "Login Successful");
                        status = loginRole;
                    }
                    else {
                        setLblError(Color.TOMATO, "Username/password doesn't exist. Try again.");
                        status = "Error";
                    }
                }
                else {
                    setLblError(Color.TOMATO, "Username/password doesn't exist. Try again.");
                    status = "Error";
                }
            } catch (SQLException e) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
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

        CustomerController.getUserLogin(getUserLogin());
        CustomerController.getRoleLogin(getUserRole());

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
