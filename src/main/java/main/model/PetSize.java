package main.model;

import javafx.beans.property.*;

public class PetSize extends Logs {

    private IntegerProperty Id;
    private StringProperty size;

    public PetSize() {
        this.Id = new SimpleIntegerProperty();
        this.size = new SimpleStringProperty();
    }

    public PetSize(String size) {
        this.Id = new SimpleIntegerProperty();
        this.size = new SimpleStringProperty();
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

    public String getSize() {
        return size.get();
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public void setSize(String size) {
        this.size.set(size);
    }
}
