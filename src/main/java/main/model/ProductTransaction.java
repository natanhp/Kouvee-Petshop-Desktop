package main.model;

import javafx.beans.property.*;

import java.util.Date;

public class ProductTransaction extends Logs {
    private StringProperty id;
    private DoubleProperty Total;
    private StringProperty Customers_id;
    private StringProperty Employees_Id;
    private StringProperty isPaid;
    private IntegerProperty itemQty;

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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

    public String getCustomers_id() {
        return Customers_id.get();
    }

    public StringProperty customers_idProperty() {
        return Customers_id;
    }

    public void setCustomers_id(String customers_id) {
        this.Customers_id.set(customers_id);
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

    public String getIsPaid() {
        return isPaid.get();
    }

    public StringProperty isPaidProperty() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid.set(isPaid);
    }

    public int getItemQty() {
        return itemQty.get();
    }

    public IntegerProperty itemQtyProperty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty.set(itemQty);
    }

    public ProductTransaction() {
        this.id = new SimpleStringProperty();
        this.Total = new SimpleDoubleProperty();
        this.Customers_id = new SimpleStringProperty();
        this.Employees_Id = new SimpleStringProperty();
        this.isPaid = new SimpleStringProperty();
        this.itemQty = new SimpleIntegerProperty();
    }
}
