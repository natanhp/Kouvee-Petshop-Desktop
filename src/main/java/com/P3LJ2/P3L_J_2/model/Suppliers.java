package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suppliers extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int Id;

    public Suppliers(int Id, String name, String address, String phoneNumber)
    {
        super(name, address,phoneNumber);

        this.Id = Id;
    }

    public int getId()
    {
        return Id;
    }

    @Override
    public String toString()
    {
        return String.format("%s", super.toString());
    }


}
