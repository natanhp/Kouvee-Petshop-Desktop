package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductTransaction{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private int Employees_Id;
    private int Customers_Id;
    private int itemQty;
    private double total;
    private boolean isPaid;

    public ProductTransaction()
    {}

    public ProductTransaction(int id, int Employees_Id, int customers_Id, int itemQty, double total, boolean isPaid) {
        Id = id;
        Employees_Id = Employees_Id;
        Customers_Id = Customers_Id;
        this.itemQty = itemQty;
        this.total = total;
        this.isPaid = isPaid;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getEmployees_Id() {
        return Employees_Id;
    }

    public void setEmployees_Id(int employees_Id) {
        Employees_Id = employees_Id;
    }

    public int getCustomers_Id() {
        return Customers_Id;
    }

    public void setCustomers_Id(int customers_Id) {
        Customers_Id = customers_Id;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "ProductTransaction{" +
                "Id=" + Id +
                ", Employees_Id=" + Employees_Id +
                ", Customers_Id=" + Customers_Id +
                ", itemQty=" + itemQty +
                ", total=" + total +
                ", isPaid=" + isPaid +
                '}';
    }
}
