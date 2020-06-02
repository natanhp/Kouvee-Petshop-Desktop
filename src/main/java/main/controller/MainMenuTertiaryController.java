package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MainMenuTertiaryController implements Initializable {

    private static String returnName;
    private static ActionEvent getEvent;

    @FXML
    private Label nameLogged;

    @FXML
    private Button btnMainLogout;

    @FXML
    private Button btnMainKeluar;

    @FXML
    private Button btnBayarLayanan;

    @FXML
    private Button btnBayarProduk;

    public static void getUnameLogin (String uname) {
        returnName = uname;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setWelcomeLabel();
    }

    private void setWelcomeLabel() {
        nameLogged.setTextFill(Color.WHITESMOKE);
        nameLogged.setText("Welcome, " + returnName);
        nameLogged.getText();
    }

    private void setImageButton() {

    }

    public void handleMainButtonAction(MouseEvent me) {

        Scene scene = null;

        if(me.getSource() == btnMainKeluar) {
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
        else if(me.getSource() != btnMainKeluar) {

            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                triggerEvent();

                if(me.getSource() == btnMainLogout)
                {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/Main.fxml")));
                }
                else if(me.getSource() == btnBayarLayanan) {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PaymentServiceView.fxml")));
                }
                else if(me.getSource() == btnBayarProduk) {
                    scene = new Scene(FXMLLoader.load(getClass().getResource("/main/PaymentProductView.fxml")));
                }

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ActionEvent getGetEvent() {
        return getEvent;
    }

    public void triggerEvent() throws SQLException, ClassNotFoundException {
        PetSecondaryController.getEvent(getGetEvent());
        CustomerSecondaryController.getEvent(getGetEvent());
    }

}
