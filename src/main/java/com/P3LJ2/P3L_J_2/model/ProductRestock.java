package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductRestock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private int itemQty;
    private int Suppliers_Id;
    private int Products_Id;
    private int Employees_Id;
    private boolean isArrived;

    public ProductRestock(int Id, int itemQty, int Suppliers_Id, int Products_Id, int Employees_Id, boolean isArrived)
    {
        this.Id = Id;
        this.itemQty = itemQty;
        this.Suppliers_Id = Suppliers_Id;
        this.Products_Id = Products_Id;
        this.Employees_Id = Employees_Id;
        this.isArrived = isArrived;
    }

    public int getId() {
        return Id;
    }

    public int getItemQty() {
        return itemQty;
    }

    public int getSuppliers_Id() {
        return Suppliers_Id;
    }

    public int getProducts_Id() {
        return Products_Id;
    }

    public int getEmployees_Id() {
        return Employees_Id;
    }

    public boolean isArrived() {
        return isArrived;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public void setSuppliers_Id(int suppliers_Id) {
        Suppliers_Id = suppliers_Id;
    }

    public void setProducts_Id(int products_Id) {
        Products_Id = products_Id;
    }

    public void setEmployees_Id(int employees_Id) {
        Employees_Id = employees_Id;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }

    @Override
    public String toString() {
        return "ProductRestock{" +
                "Id=" + Id +
                ", itemQty=" + itemQty +
                ", Suppliers_Id=" + Suppliers_Id +
                ", Products_Id=" + Products_Id +
                ", Employees_Id=" + Employees_Id +
                ", isArrived=" + isArrived +
                '}';
    }
}
