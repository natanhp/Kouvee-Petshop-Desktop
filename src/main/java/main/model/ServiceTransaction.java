package main.model;

import javafx.beans.property.*;

import java.util.Date;

public class ServiceTransaction extends Logs{
    private StringProperty id;
    private SimpleObjectProperty<Date> date;
    private DoubleProperty Total;
    private StringProperty Pets_Id;
    private StringProperty Employees_Id;
    private StringProperty isPaid;

    public ServiceTransaction() {
        this.id = new SimpleStringProperty();
        this.date = new SimpleObjectProperty();
        this.Total = new SimpleDoubleProperty();
        this.Pets_Id = new SimpleStringProperty();
        this.Employees_Id = new SimpleStringProperty();
        this.isPaid = new SimpleStringProperty();
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
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

    public String getPets_Id() {
        return Pets_Id.get();
    }

    public StringProperty pets_IdProperty() {
        return Pets_Id;
    }

    public void setPets_Id(String pets_Id) {
        this.Pets_Id.set(pets_Id);
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

    public String isIsPaid() {
        return isPaid.get();
    }

    public StringProperty isPaidProperty() {
        return isPaid;
    }

    public void setIsPaid(String isPaid) {
        this.isPaid.set(isPaid);
    }
}
