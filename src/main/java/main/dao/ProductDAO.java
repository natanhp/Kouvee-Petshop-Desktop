package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.controller.ProductController;
import main.model.Customer;
import main.model.Pet;
import main.model.Product;
import main.util.DBUtil;
import sun.rmi.runtime.Log;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ProductDAO {
    //SELECT a Product
    public static Product searchProduct(String prName) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM products WHERE productName ='" + prName + "';";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPr = DBUtil.dbExecuteQuery(selectStmt);

            Product product = getProductsFromResultSet(rsPr);

            return product;
        } catch (SQLException ex) {
            System.out.println("While searching a product with Id : " + prName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Product getProductsFromResultSet(ResultSet rs) throws SQLException {

        Product pr = null;
        if(rs.next()) {
            String newString = null;
            pr = new Product(newString);
            pr.setId(rs.getInt("id"));
            pr.setProductName(rs.getString("productName"));
            pr.setProductQuantity(rs.getInt("productQuantity"));
            pr.setMeassurement(rs.getString("meassurement"));
            pr.setProductPrice(rs.getInt("productPrice"));
            pr.setMinimumQuantity(rs.getInt("minimumQty"));
//            pr.setImage(rs.getBytes("image"));
        }

        return pr;
    }

    //SELECT Products
    public static ObservableList<Product> searchProducts() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM products";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPrs = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getProductList method and get product object
            ObservableList<Product> prList = getProductList(rsPrs);

            //Return Product Object
            return prList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM products operation
    public static ObservableList<Product> getProductList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Customer Objects
        ObservableList<Product> prList = FXCollections.observableArrayList();

        while(rs.next()) {
            Product pr;
            String newString = null;
            pr = new Product(newString);
            pr.setId(rs.getInt("id"));
            pr.setProductName(rs.getString("productName"));
            pr.setProductQuantity(rs.getInt("productQuantity"));
            pr.setMeassurement(rs.getString("meassurement"));
            pr.setProductPrice(rs.getInt("productPrice"));
            pr.setMinimumQuantity(rs.getInt("minimumQty"));
//            pr.setImage(rs.getBytes("image"));

            //Add product to the ObservableList
            prList.add(pr);
        }

        //Return prList (ObservableList of Products)
        return prList;
    }

    //Update an products's entries
    public static void updateEntries (String Logged, String Id, String name, String meas, String prQty,
                                      String price, String minQty, String image)
            throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE customers " +
                        "SET productName = '" + name + "' " +
                        ", productQuantity = '" + prQty + "' " +
                        ", meassurement = '" + meas + "' " +
                        ", productPrice = '" + price + "' " +
                        ", minimumQty = '" + minQty + "' " +
                        ", image = ?" +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        "WHERE id = '" + Id + "';";

        try {
            DBUtil.dbSpecialExecuteUpdate(updateStmt, image);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE a product
    public static void deletePrWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM products " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //SOFT DELETE a product
    public static void softDeletePrWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE products " +
                        "SET productName = NULL" +
                        ", productQuantity = NULL" +
                        ", meassurement = NULL" +
                        ", productPrice = NULL" +
                        ", minimumQty = NULL" +
                        ", image = NULL" +
                        ", deletedAt = NOW()" +
                        ", deletedBy " + Logged +
                        "WHERE id = '" + Id + "';";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " +ex);
            throw ex;
        }
    }

    //INSERT a Customer
    public static void insertPr(String Logged, String name, String prQty, String meas,
                                String price, String minQty, String image)
            throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO products " +
                        "(image, productName, productQuantity, meassurement, productPrice, minimumQty, createdAt, createdBy)" +
                        "VALUES " +
                    "(?,'" +
                        name + "','" + prQty + "','" + meas +
                        "','" + price + "','" + minQty + "'," + "NOW()" + ",'" + Logged + "');";

        try {
            System.out.println(image);
            DBUtil.dbSpecialExecuteUpdate(updateStmt, image);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
