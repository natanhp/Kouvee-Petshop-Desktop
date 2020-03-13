package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private int Products_Id;
    private String ProductTransaction_Id;

    public ProductTransactionDetail(int id, int Products_Id, String ProductTransaction_Id) {

        this.Id = id;
        this.Products_Id = Products_Id;
        this.ProductTransaction_Id = ProductTransaction_Id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getProducts_Id() {
        return Products_Id;
    }

    public void setProducts_Id(int products_Id) {
        Products_Id = products_Id;
    }

    public String getProductTransaction_Id() {
        return ProductTransaction_Id;
    }

    public void setProductTransaction_Id(String productTransaction_Id) {
        ProductTransaction_Id = productTransaction_Id;
    }

    @Override
    public String toString() {
        return "ProductTransactionDetail{" +
                "Id=" + Id +
                ", Products_Id=" + Products_Id +
                ", ProductTransaction_Id='" + ProductTransaction_Id + '\'' +
                '}';
    }
}
