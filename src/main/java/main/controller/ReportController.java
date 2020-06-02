package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.util.DBUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class ReportController extends JFrame {
    @FXML
    private Button btnMenuUtama;

    @FXML
    private Button btnLaporanKeluar;

    @FXML
    private Button btnPendapatanT;

    @FXML
    private Button btnProdukT;

    @FXML
    private Button btnLayananT;

    @FXML
    private Button btnPendapatanB;

    @FXML
    private Button btnPengadaanT;

    @FXML
    private Button btnPengadaanB;

    @FXML
    private Button btnCetakLaporan;

    @FXML
    private ComboBox<?> comboMonth;

    @FXML
    private ComboBox<?> comboYear;


    @FXML
    void handleButtonReport(MouseEvent me) {

        if (me.getSource() == btnLaporanKeluar) {
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

    @FXML
    void handleButtonShowReport(MouseEvent me) {
        if(me.getSource() == btnPendapatanT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportEYearView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnPendapatanB)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportEMonthView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnLayananT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportServiceSView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnProdukT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportProductSView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if(me.getSource() == btnPengadaanT)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportPYearView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
        if(me.getSource() == btnPengadaanB)
        {
            Node node = (Node) me.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/main/ReportPMonthView.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }
    private static final long serialVersionUID = 1L;

    public void showReport() throws JRException, SQLException {

        String reportSrcFile = "D:\\denni\\Kouvee-Petshop-Desktop\\src\\main\\resources\\reports\\Laporan_Pendapatan_Tahunan.jrxml";

        // First, compile jrxml file.
        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for report
        HashMap<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("company", "MAROTHIA TECHS");
        parameters.put("receipt_no", "RE101".toString());
        parameters.put("name", "Khushboo");
        parameters.put("amount", "10000");
        parameters.put("receipt_for", "EMI Payment");
        parameters.put("date", "20-12-2016");
        parameters.put("contact", "98763178".toString());

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

        Connection connection = DBUtil.conDB();

        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, connection);
        JRViewer viewer = new JRViewer(print);viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);

        System.out.print("Done!");

//        Properties properties = new Properties();
//        JasperReport jasRep;
//        JasperPrint jasPri;
//        JasperDesign jasDes;
//
//        try {
//            properties.load(new FileInputStream("setting.properties"));
//            String driverName = properties.getProperty("driverName");
//            try {
//                Class.forName(driverName);
//                File report = new File("src/main/resources/reports/Laporan_Pendapatan_Tahunan.jrxml");
//                jasDes = JRXmlLoader.load(report);
//                HashMap parameter = new HashMap();
//                parameter.put("bulan_query", bulan.getSelectionModel().getSelectedItem().toString()+"/"+tahun.getText());
//                parameter.put("bulan_text", bulan.getSelectionModel().getSelectedItem().toString()+" "+tahun.getText());
//                parameter.put("total", total_uang_keluar.getText());
//                parameter.put()
//                jasRep = JasperCompileManager.compileReport(jasDes);
//                jasPri = JasperFillManager.fillReport(jasRep, parameter, kon.con);
//                jasPri= JasperFillManager.fillReportToFile(jasRep, )
//                JasperViewer.viewReport(jasPri, false);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, e);
//            }
//        } catch (Exception e) {
//        }

//        Connection connection;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//
//            connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/9509?useOldAliasMetadataBehavior=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
//
//            System.out.println("Filling report....");
//            JasperPrint print = JasperFillManager.fillReport("src/main/resources/reports/Laporan_Pendapatan_Tahunan.jasper", new HashMap<>(), connection);
//            JRViewer viewer = new JRViewer(print);viewer.setOpaque(true);
//            viewer.setVisible(true);
//            this.add(viewer);
//            this.setSize(700, 500);
//            this.setVisible(true);
//            System.out.println("Done !");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    @FXML
    public void printReport(ActionEvent ae) {
        try {
            // --- Show Jasper Report on click-----
            showReport();
        } catch (JRException | SQLException e1) {
            e1.printStackTrace();
        }
    }
}
