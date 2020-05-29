package main.model;

import javafx.beans.property.*;

import java.util.Date;

public class ServiceTransactionDetail extends Logs{

    private IntegerProperty id;
    private StringProperty isFinished;
    private IntegerProperty serviceDetails_Id;
    private StringProperty serviceTransaction_Id;
    private SimpleObjectProperty<Date> date;
    private DoubleProperty Total;
    private StringProperty Pets_Id;
    private StringProperty Employees_Id;
    private StringProperty serviceName;
    private byte[] isPaid;

    public ServiceTransactionDetail(byte[] isPaid) {
        this.id = new SimpleIntegerProperty();
        this.isFinished = new SimpleStringProperty();
        this.serviceDetails_Id = new SimpleIntegerProperty();
        this.serviceTransaction_Id = new SimpleStringProperty();
        this.date = new SimpleObjectProperty();
        this.Total = new SimpleDoubleProperty();
        this.Pets_Id = new SimpleStringProperty();
        this.Employees_Id = new SimpleStringProperty();
        this.serviceName = new SimpleStringProperty();
        this.isPaid = isPaid;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getServiceDetails_Id() {
        return serviceDetails_Id.get();
    }

    public IntegerProperty serviceDetails_IdProperty() {
        return serviceDetails_Id;
    }

    public void setServiceDetails_Id(int serviceDetails_Id) {
        this.serviceDetails_Id.set(serviceDetails_Id);
    }

    public String getServiceTransaction_Id() {
        return serviceTransaction_Id.get();
    }

    public StringProperty serviceTransaction_IdProperty() {
        return serviceTransaction_Id;
    }

    public void setServiceTransaction_Id(String serviceTransaction_Id) {
        this.serviceTransaction_Id.set(serviceTransaction_Id);
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

    public String getServiceId() {
        return serviceName.get();
    }

    public StringProperty serviceIdProperty() {
        return serviceName;
    }

    public void setServiceId(String serviceId) {
        this.serviceName.set(serviceId);
    }

    public StringProperty getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished.set(isFinished);
    }

    public byte[] getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(byte[] isPaid) {
        this.isPaid = isPaid;
    }
}
