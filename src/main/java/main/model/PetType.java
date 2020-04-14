package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PetType {

    private IntegerProperty Id;
    private StringProperty type;

    public PetType() {
        this.Id = new SimpleIntegerProperty();
        this.type = new SimpleStringProperty();
    }

    public PetType(String type) {
        this.Id = new SimpleIntegerProperty();
        this.type = new SimpleStringProperty();
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
