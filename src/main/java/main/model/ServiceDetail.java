package main.model;

import javafx.beans.property.*;

public class ServiceDetail extends Logs{
    private IntegerProperty id;
    private StringProperty serviceName;
    private StringProperty petTypeName;
    private StringProperty petSizeName;
    private DoubleProperty price;
    private StringProperty completeName;

    public ServiceDetail() {
        this.id = new SimpleIntegerProperty();
        this.serviceName = new SimpleStringProperty();
        this.petTypeName = new SimpleStringProperty();
        this.petSizeName = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.completeName = new SimpleStringProperty();
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

    public String getServiceId() {
        return serviceName.get();
    }

    public StringProperty serviceIdProperty() {
        return serviceName;
    }

    public void setServiceId(String serviceId) {
        this.serviceName.set(serviceId);
    }

    public String getPetTypeId() {
        return petTypeName.get();
    }

    public StringProperty petTypeIdProperty() {
        return petTypeName;
    }

    public void setPetTypeId(String petTypeId) {
        this.petTypeName.set(petTypeId);
    }

    public String getPetSizeId() {
        return petSizeName.get();
    }

    public StringProperty petSizeIdProperty() {
        return petSizeName;
    }

    public void setPetSizeId(String petSizeId) {
        this.petSizeName.set(petSizeId);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getCompleteName() {
        return completeName.get();
    }

    public StringProperty completeNameProperty() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName.set(completeName);
    }
}
