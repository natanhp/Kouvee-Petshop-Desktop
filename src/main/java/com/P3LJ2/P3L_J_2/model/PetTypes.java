package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PetTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private String type;

    public PetTypes(int id, String type) {
        Id = id;
        this.type = type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PetTypes{" +
                "Id=" + Id +
                ", type='" + type + '\'' +
                '}';
    }
}
