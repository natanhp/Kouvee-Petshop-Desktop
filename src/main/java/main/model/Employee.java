package main.model;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Employee extends Person {

    private SimpleObjectProperty<Date> dateBirth;
    private StringProperty role;
    private StringProperty username;
    private StringProperty password;

    public Employee() {

        this.dateBirth = new SimpleObjectProperty();
        this.role = new SimpleStringProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();

    }

    public Object getDateBirth() {
        return dateBirth.get();
    }

    public SimpleObjectProperty dateBirthProperty() {
        return dateBirth;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth.set(dateBirth);
    }

    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
