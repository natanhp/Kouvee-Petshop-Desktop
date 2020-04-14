package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Product;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    //SELECT a Product
    public static ObservableList<Product> searchProduct(String prName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM Products WHERE productName LIKE '%" + prName + "%' AND deletedAt IS NULL;";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPr = DBUtil.dbExecuteQuery(selectStmt);

            return getProductList(rsPr);
        } catch (SQLException ex) {
            System.out.println("While searching a product with Id : " + prName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Product getProductsFromResultSet(ResultSet rs) throws SQLException {

        Product pr = null;
        if (rs.next()) {
            pr = new Product(null, null);
            pr.setId(rs.getInt("id"));
            pr.setProductName(rs.getString("productName"));
            pr.setProductQuantity(rs.getInt("productQuantity"));
            pr.setMeassurement(rs.getString("meassurement"));
            pr.setProductPrice(rs.getInt("productPrice"));
            pr.setMinimumQuantity(rs.getInt("minimumQty"));
//            pi = rs.getBytes("image");
            pr.setImage(rs.getBytes("image"));
        }

        return pr;
    }

    //SELECT Products
    public static ObservableList<Product> searchProducts() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Products WHERE deletedAt IS NULL";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPrs = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getProductList method and get product object

            //Return Product Object
            return getProductList(rsPrs);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM products operation
    public static ObservableList<Product> getProductList(ResultSet rs) throws SQLException {

        //Declare a observable List which comprises of Customer Objects
        ObservableList<Product> prList = FXCollections.observableArrayList();

        while (rs.next()) {
            Product pr;
            pr = new Product(null, null);
            pr.setId(rs.getInt("id"));
            pr.setProductName(rs.getString("productName"));
            pr.setProductQuantity(rs.getInt("productQuantity"));
            pr.setMeassurement(rs.getString("meassurement"));
            pr.setProductPrice(rs.getInt("productPrice"));
            pr.setMinimumQuantity(rs.getInt("minimumQty"));
            pr.setImage(rs.getBytes("image"));

            //Add product to the ObservableList
            prList.add(pr);
        }

        //Return prList (ObservableList of Products)
        return prList;
    }

    //Update an products's entries
    public static void updateEntries(String Logged, String Id, String name, String meas, String prQty,
                                     String price, String minQty, String image)
            throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Products " +
                        "SET productName = '" + name + "' " +
                        ", productQuantity = '" + prQty + "' " +
                        ", meassurement = '" + meas + "' " +
                        ", productPrice = '" + price + "' " +
                        ", minimumQty = '" + minQty + "' " +
                        ", image = ?" +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        "WHERE id = '" + Id + "' AND deletedAt IS NULL;";

        try {
            DBUtil.dbSpecialExecuteUpdate(updateStmt, image);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    public static void updateEntriesNoImage(String Logged, String Id, String name, String meas, String prQty,
                                            String price, String minQty)
            throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Products " +
                        "SET productName = '" + name + "' " +
                        ", productQuantity = '" + prQty + "' " +
                        ", meassurement = '" + meas + "' " +
                        ", productPrice = '" + price + "' " +
                        ", minimumQty = '" + minQty + "' " +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        "WHERE id = '" + Id + "' AND deletedAt IS NULL;";

        DBUtil.dbExecuteUpdate(updateStmt);
    }

    //DELETE a product
    public static void deletePrWithId(String Id) throws SQLException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM Products " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE a product
    public static void softDeletePrWithId(String Logged, String Id) throws SQLException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Products " +
                        "SET " +
                        "deletedAt = NOW()" +
                        ", deletedBy = " + Logged +
                        " WHERE id = '" + Id + "' AND deletedAt IS NULL;";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " + ex);
            throw ex;
        }
    }

    //INSERT a Customer
    public static void insertPr(String Logged, String name, String prQty, String meas,
                                String price, String minQty, String image)
            throws SQLException {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Products " +
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
