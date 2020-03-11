package com.P3LJ2.P3L_J_2;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class Suppliers extends User{

    public Suppliers(int Id, String name, String address, String phoneNumber)
    {
        super(Id, name, address,phoneNumber);
    }

    @Override
    public String toString()
    {
        return String.format("%s", super.toString());
    }


}
