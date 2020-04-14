package main.model;

import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class Customer extends Person {

    private SimpleObjectProperty<Date> dateBirth;

    public Customer() {
        this.dateBirth = new SimpleObjectProperty();
    }

    public Object getDateBirth() {
        return dateBirth.get();
    }

    public SimpleObjectProperty dateBirthProperty() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth.set(dateBirth);
    }
}
