package com.P3LJ2.P3L_J_2;

public class Products {
    private String uniqueId;
    private String productName;
    private String meassurement;
    private int productQty;
    private int minimumQty;
    private double price;
    private String image;

    public Products(String uniqueId, String productName, String meassurement, String image,
                    int productQty, int minimumQty, double price)
    {
        this.uniqueId = uniqueId;
        this.productName = productName;
        this.meassurement = meassurement;
        this.image = image;
        this.productQty = productQty;
        this.minimumQty = minimumQty;
        this.price = price;
    }

    public String getUniqueId()
    {
        return uniqueId;
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

    @Override
    public String toString()
    {
        return String.format("%s%n", uniqueId);

    }

}
