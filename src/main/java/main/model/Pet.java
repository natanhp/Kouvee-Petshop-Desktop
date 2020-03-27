package main.model;

import javafx.beans.property.*;

import java.util.Date;

public class Pet {

    private IntegerProperty Id;
    private IntegerProperty Customer_Id;
    private IntegerProperty PetSize_Id;
    private IntegerProperty PetType_Id;
    private StringProperty name;
    private SimpleObjectProperty<Date> dateBirth;

    public Pet() {
        this.Id = new SimpleIntegerProperty();
        this.Customer_Id = new SimpleIntegerProperty();
        this.PetSize_Id = new SimpleIntegerProperty();
        this.PetType_Id = new SimpleIntegerProperty();
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

    public int getCustomer_Id() {
        return Customer_Id.get();
    }

    public IntegerProperty customer_IdProperty() {
        return Customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        this.Customer_Id.set(customer_Id);
    }

    public int getPetSize_Id() {
        return PetSize_Id.get();
    }

    public IntegerProperty petSize_IdProperty() {
        return PetSize_Id;
    }

    public void setPetSize_Id(int petSize_Id) {
        this.PetSize_Id.set(petSize_Id);
    }

    public int getPetType_Id() {
        return PetType_Id.get();
    }

    public IntegerProperty petType_IdProperty() {
        return PetType_Id;
    }

    public void setPetType_Id(int petType_Id) {
        this.PetType_Id.set(petType_Id);
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
