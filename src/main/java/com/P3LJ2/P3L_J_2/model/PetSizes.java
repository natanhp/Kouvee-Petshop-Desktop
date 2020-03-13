package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PetSizes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String size;

    public PetSizes(int id, String size) {
        Id = id;
        this.size = size;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PetSizes{" +
                "Id=" + Id +
                ", size='" + size + '\'' +
                '}';
    }
}
