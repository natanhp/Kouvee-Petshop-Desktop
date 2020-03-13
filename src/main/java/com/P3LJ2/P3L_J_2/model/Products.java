package com.P3LJ2.P3L_J_2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String productName;
    private String meassurement;
    private int productQty;
    private int minimumQty;
    private double price;
    private String image;

    public Products(int Id, String productName, String meassurement, String image,
                    int productQty, int minimumQty, double price)
    {
        this.Id = Id;
        this.productName = productName;
        this.meassurement = meassurement;
        this.image = image;
        this.productQty = productQty;
        this.minimumQty = minimumQty;
        this.price = price;
    }

    public int getId()
    {
        return Id;
    }

    public String getProductName()
    {
        return productName;
    }

    public String getMeassurement()
    {
        return meassurement;
    }

    public String getImage()
    {
        return image;
    }

    public int getProductQty()
    {
        return productQty;
    }

    public int getMinimumQty()
    {
        return minimumQty;
    }

    public double getPrice()
    {
        return price;
    }

    public void setUniqueId(String uniqueId) {
        this.Id = Id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setMeassurement(String meassurement) {
        this.meassurement = meassurement;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public void setMinimumQty(int minimumQty) {
        this.minimumQty = minimumQty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Products{" +
                "Id='" + Id + '\'' +
                ", productName='" + productName + '\'' +
                ", meassurement='" + meassurement + '\'' +
                ", productQty=" + productQty +
                ", minimumQty=" + minimumQty +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

}
