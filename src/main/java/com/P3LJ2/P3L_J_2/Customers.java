package com.P3LJ2.P3L_J_2;

public class Customers extends User {

    public Customers(int Id, String name, String address, String dateBirth, String phoneNumber)
    {
        super(Id, name, address, dateBirth, phoneNumber);
    }

    @Override
    public String toString()
    {
        return String.format("%s", super.toString());
    }
}
