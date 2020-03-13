package com.P3LJ2.P3L_J_2.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
//@Access(value= AccessType.PROPERTY)
public class Employees extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    //eight-argument constructor
    public Employees(int Id, String name, String address, String dateBirth, String phoneNumber, String role, String username, String password)
    {
        super(name, address, dateBirth, phoneNumber , role, username, password);
        this.Id = Id;
    }

    @Override
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString()
    {
        return String.format("%s", super.toString());
    }
}
