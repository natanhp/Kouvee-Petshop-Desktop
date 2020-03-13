package com.p3lj2.dao;

import com.p3lj2.model.ProductModel;
import com.p3lj2.util.DatabaseInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private DatabaseInstance databaseInstance;

    public ProductDao() {
        this.databaseInstance = DatabaseInstance.getDatabaseInstance();
    }

    public List<ProductModel> getAllProduct() throws SQLException {
        Connection connection = databaseInstance.getConnection();
        String query = "SELECT productName FROM Products";
        List<ProductModel> productModels = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    ProductModel productModel = new ProductModel();
                    productModel.setProductName(resultSet.getString("productName"));
                    productModels.add(productModel);
                }
            }

            resultSet.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            connection.close();
        }

        return productModels;
    }
}
