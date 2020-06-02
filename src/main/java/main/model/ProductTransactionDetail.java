package main.model;

import javafx.beans.property.*;

import java.util.Date;

public class ProductTransactionDetail {

    private IntegerProperty id;
    private IntegerProperty Products_Id;
    private StringProperty ProductTransaction_Id;
    private StringProperty productName;
    private DoubleProperty productPrice;
    private StringProperty Employees_Id;
    private StringProperty Customers_Id;
    private DoubleProperty Total;
    private byte[] isPaid;


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getProducts_Id() {
        return Products_Id.get();
    }

    public IntegerProperty products_IdProperty() {
        return Products_Id;
    }

    public void setProducts_Id(int products_Id) {
        this.Products_Id.set(products_Id);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public double getProductPrice() {
        return productPrice.get();
    }

    public DoubleProperty productPriceProperty() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice.set(productPrice);
    }

    public String getProductTransaction_Id() {
        return ProductTransaction_Id.get();
    }

    public StringProperty productTransaction_IdProperty() {
        return ProductTransaction_Id;
    }

    public void setProductTransaction_Id(String productTransaction_Id) {
        this.ProductTransaction_Id.set(productTransaction_Id);
    }

    public String getEmployees_Id() {
        return Employees_Id.get();
    }

    public StringProperty employees_IdProperty() {
        return Employees_Id;
    }

    public void setEmployees_Id(String employees_Id) {
        this.Employees_Id.set(employees_Id);
    }

    public String getCustomers_Id() {
        return Customers_Id.get();
    }

    public StringProperty customers_IdProperty() {
        return Customers_Id;
    }

    public void setCustomers_Id(String customers_Id) {
        this.Customers_Id.set(customers_Id);
    }

    public double getTotal() {
        return Total.get();
    }

    public DoubleProperty totalProperty() {
        return Total;
    }

    public void setTotal(double total) {
        this.Total.set(total);
    }

    public byte[] getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(byte[] isPaid) {
        this.isPaid = isPaid;
    }

    public ProductTransactionDetail(byte[] isPaid) {
        this.id = new SimpleIntegerProperty();
        this.Products_Id = new SimpleIntegerProperty();
        this.productName = new SimpleStringProperty();
        this.productPrice = new SimpleDoubleProperty();
        this.ProductTransaction_Id = new SimpleStringProperty();
        this.Employees_Id = new SimpleStringProperty();
        this.Customers_Id = new SimpleStringProperty();
        this.Total = new SimpleDoubleProperty();
        this.isPaid = isPaid;
    }
}
