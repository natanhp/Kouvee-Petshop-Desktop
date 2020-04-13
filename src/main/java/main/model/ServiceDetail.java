package main.model;

import javafx.beans.property.*;

public class ServiceDetail {
    private IntegerProperty id;
    private IntegerProperty serviceId;
    private IntegerProperty petTypeId;
    private IntegerProperty petSizeId;
    private DoubleProperty price;
    private StringProperty completeName;

    public ServiceDetail() {
        this.id = new SimpleIntegerProperty();
        this.serviceId = new SimpleIntegerProperty();
        this.petTypeId = new SimpleIntegerProperty();
        this.petSizeId = new SimpleIntegerProperty();
        this.price = new SimpleDoubleProperty();
        this.completeName = new SimpleStringProperty();
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

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getServiceId() {
        return serviceId.get();
    }

    public IntegerProperty serviceIdProperty() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId.set(serviceId);
    }

    public int getPetTypeId() {
        return petTypeId.get();
    }

    public IntegerProperty petTypeIdProperty() {
        return petTypeId;
    }

    public void setPetTypeId(int petTypeId) {
        this.petTypeId.set(petTypeId);
    }

    public int getPetSizeId() {
        return petSizeId.get();
    }

    public IntegerProperty petSizeIdProperty() {
        return petSizeId;
    }

    public void setPetSizeId(int petSizeId) {
        this.petSizeId.set(petSizeId);
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
}
