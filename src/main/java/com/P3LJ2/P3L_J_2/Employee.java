package com.P3LJ2.P3L_J_2;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Employee extends User{

    //eight-argument constructor
    public Employee(int Id, String name, String address, String dateBirth, String phoneNumber, String role, String username, String password)
    {
        super(Id, name, address, dateBirth, phoneNumber , role, username, password);
    }

    @Override
    public String toString()
    {
        return String.format("%s", super.toString());
    }
}
