package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pets extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    public Pets() {
    }

    public Pets(int Id, String name, String dateBirth, String Customers_id, String PetSizes_id, String PetTypes_id) {
        super(name, dateBirth, Customers_id, PetSizes_id, PetTypes_id);

        this.Id = Id;
    }

    public int getId()
    {
        return Id;
    }

    @Override
    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "Id=" + Id +
                '}';
    }
}
