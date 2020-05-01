package main.model;

import javafx.animation.Interpolator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.util.List;
import  javafx.beans.property.*;

public class Person extends Object {

    private IntegerProperty Id;
    private StringProperty name;
    private StringProperty address;
    private StringProperty phoneNumber;

    private SimpleObjectProperty<Timestamp> createdAt;
    private SimpleObjectProperty<Timestamp> updatedAt;
    private SimpleObjectProperty<Timestamp> deletedAt;
    private StringProperty createdBy;
    private StringProperty updatedBy;
    private StringProperty deletedBy;

    public Person() {

        this.Id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();

        this.createdAt = new SimpleObjectProperty();
        this.updatedAt = new SimpleObjectProperty();
        this.deletedAt = new SimpleObjectProperty();
        this.createdBy = new SimpleStringProperty();
        this.updatedBy = new SimpleStringProperty();
        this.deletedBy = new SimpleStringProperty();
    }

    public int getId() {
        return Id.get();
    }

    public IntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }



    public Object getCreatedAt() {
        return createdAt.get();
    }

    public SimpleObjectProperty createdAtProperty() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt.set(createdAt);
    }

    public Object getUpdatedAt() {
        return updatedAt.get();
    }

    public SimpleObjectProperty updatedAtProperty() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt.set(updatedAt);
    }

    public Object getDeletedAt() {
        return deletedAt.get();
    }

    public SimpleObjectProperty deletedAtProperty() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt.set(deletedAt);
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public StringProperty createdByProperty() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }

    public String getUpdatedBy() {
        return updatedBy.get();
    }

    public StringProperty updatedByProperty() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy.set(updatedBy);
    }

    public String getDeletedBy() {
        return deletedBy.get();
    }

    public StringProperty deletedByProperty() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy.set(deletedBy);
    }
}
