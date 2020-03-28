package main.model;

import javafx.beans.property.*;

import java.util.Date;

public class Pet {

    private IntegerProperty Id;
    private StringProperty Customer_name;
    private StringProperty PetSize_name;
    private StringProperty PetType_name;
    private StringProperty name;
    private SimpleObjectProperty<Date> dateBirth;

    public Pet() {
        this.Id = new SimpleIntegerProperty();
        this.Customer_name = new SimpleStringProperty();
        this.PetSize_name = new SimpleStringProperty();
        this.PetType_name = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.dateBirth = new SimpleObjectProperty<>();
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

    public String getCustomer_name() {
        return Customer_name.get();
    }

    public StringProperty customer_nameProperty() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.Customer_name.set(customer_name);
    }

    public String getPetSize_name() {
        return PetSize_name.get();
    }

    public StringProperty petSize_nameProperty() {
        return PetSize_name;
    }

    public void setPetSize_name(String petSize_name) {
        this.PetSize_name.set(petSize_name);
    }

    public String getPetType_name() {
        return PetType_name.get();
    }

    public StringProperty petType_nameProperty() {
        return PetType_name;
    }

    public void setPetType_name(String petType_name) {
        this.PetType_name.set(petType_name);
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

    public Date getDateBirth() {
        return dateBirth.get();
    }

    public SimpleObjectProperty<Date> dateBirthProperty() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth.set(dateBirth);
    }
}
