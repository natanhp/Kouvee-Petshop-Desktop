package main.model;

import javafx.beans.property.*;

public class ServiceTransactionDetail extends Logs{

    private IntegerProperty id;
    private BooleanProperty isFinished;
    private IntegerProperty serviceDetails_Id;
    private StringProperty serviceTransaction_Id;

    public ServiceTransactionDetail() {
        this.id = new SimpleIntegerProperty();
        this.isFinished = new SimpleBooleanProperty();
        this.serviceDetails_Id = new SimpleIntegerProperty();
        this.serviceTransaction_Id = new SimpleStringProperty();
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

    public boolean isIsFinished() {
        return isFinished.get();
    }

    public BooleanProperty isFinishedProperty() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished.set(isFinished);
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
}
