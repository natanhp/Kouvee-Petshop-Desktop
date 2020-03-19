package main.model;

import javafx.animation.Interpolator;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.util.List;
import  javafx.beans.property.*;

public class Person extends Object {

    private IntegerProperty Id;
    private StringProperty name;
    private StringProperty address;
    private StringProperty phoneNumber;

    public Person() {

        this.Id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
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
}
