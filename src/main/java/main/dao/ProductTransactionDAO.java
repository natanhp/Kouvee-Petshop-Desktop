package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Product;
import main.model.ProductTransaction;
import main.model.ServiceTransaction;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductTransactionDAO {
    //SELECT a Product transaction
    public static ObservableList<ProductTransaction> searchTransaction(String transactionName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT pt.id, pt.total AS total, c.name AS 'Customers_name', emp.name AS 'Employees_name', pt.isPaid AS isPaid" +
                "                 FROM Producttransaction AS pt " +
                "                 JOIN Customers AS c ON c.id = pt.Customers_id "+
                "                 JOIN Employees AS emp ON emp.id = pt.Employees_id " +
                "                 WHERE pt.id = '" + transactionName + "'" +
                "                AND pt.deletedAt IS NULL";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTran = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<ProductTransaction> tran = FXCollections.observableArrayList();
            try {
                tran = getTranList(rsTran);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return tran;
        } catch (SQLException ex) {
            System.out.println("While searching a product transaction with Id : " + transactionName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static ProductTransaction getTransactionFromResultSet(ResultSet rs) throws SQLException {
        ProductTransaction pt = null;

        if (rs.next()) {
            pt = new ProductTransaction();
            pt.setId(rs.getString("id"));
            pt.setTotal(rs.getDouble("total"));
            pt.setCustomers_id(rs.getString("Customers_name"));
            pt.setEmployees_Id(rs.getString("Employees_name"));
            pt.setIsPaid(rs.getString("isPaid"));
        }
        return pt;
    }

    //SELECT ServiceTransactions
    public static ObservableList<ProductTransaction> searchProductTransactions() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT pt.id, pt.total AS total, c.name AS 'Customers_name', emp.name AS 'Employees_name', pt.isPaid AS isPaid" +
                "                 FROM Producttransaction AS pt " +
                "                 JOIN Customers AS c ON c.id = pt.Customers_id "+
                "                 JOIN Employees AS emp ON emp.id = pt.Employees_id " +
                "                WHERE pt.deletedAt IS NULL";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTrans = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getPetList method and get pet object

            //Return ST Object
            return getTranList(rsTrans);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM Producttransaction operation
    public static ObservableList<ProductTransaction> getTranList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of ServiceTransaction Objects
        ObservableList<ProductTransaction> tranList = FXCollections.observableArrayList();

        while (rs.next()) {

            ProductTransaction pt = new ProductTransaction();
            pt = new ProductTransaction();
            pt.setId(rs.getString("id"));
            pt.setTotal(rs.getDouble("total"));
            pt.setCustomers_id(rs.getString("Customers_name"));
            pt.setEmployees_Id(rs.getString("Employees_name"));
            byte[] target = rs.getBytes("isPaid");
            String string = new String(target);
            pt.setIsPaid(string);
//            st.setCreatedAt(rs.getTimestamp("createdAt"));
//            st.setUpdatedAt(rs.getTimestamp("updatedAt"));
//            st.setDeletedAt(rs.getTimestamp("deletedAt"));
//            st.setCreatedBy(rs.getString("Name Created"));
//            st.setUpdatedBy(rs.getString("Name Updated"));
//            st.setDeletedBy(rs.getString("Name Deleted"));

            //Add pet to the ObservableList
            tranList.add(pt);

        }

        //Return petList (ObservableList of Pets
        return tranList;
    }

    //Update a producttransaction's entries
    public static void updateEntries(String Logged, String Id, String Products_Id, String Employees_id,
                                     String Customers_id, int isPaid)
            throws SQLException, ClassNotFoundException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Producttransaction" +
                        "    , total = total + (SELECT productPrice FROM products WHERE id = '" + Products_Id + "')" +
                        "    , Customers_id = '" + Customers_id + "' " +
                        "    , Employees_id = '" + Employees_id + "' " +
                        "    , updatedBy = '" + Logged + "' " +
                        "    , updatedAt = NOW() " +
                        "    , isPaid = '" + isPaid + "' " +
                        "    WHERE id = '" + Id + "'";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //Update a producttransaction's entries
    public static void updatePayment(String Logged, String Id, double total, int isPaid)
            throws SQLException, ClassNotFoundException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Producttransaction" +
                        " SET" +
                        " total = '" + total + "' "+
                        ", updatedBy = '" + Logged + "' " +
                        ", updatedAt = NOW() " +
                        ", isPaid = '" + isPaid + "' " +
                        " WHERE id = '" + Id + "'";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE a servicetransaction
    public static void deleteTransactionWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM Producttransaction " +
                        "WHERE id = '" + Id + "' AND isPaid = '0';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE an transaction
    public static void softDeleteTransactionWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Producttransaction " +
                        "SET " +
                        "deletedAt = NOW()" +
                        ", deletedBy = " + Logged + " " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " + ex);
            throw ex;
        }
    }

    //INSERT a Service Transaction
    public static void insertTransaction(String Logged, String Id, String Customers_id,
                                         String Employees_id, int itemQty,int isPaid)
            throws SQLException {
        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Producttransaction " +
                        " (id, total , Employees_id, Customers_id, itemQty" +
                        "    , createdBy, createdAt " +
                        "    , isPaid) " +
                        "    VALUES  " +
                        "    ('" + Id + "', NULL,'" + Employees_id + "','" + Customers_id + "', '" + itemQty + "', '" + Logged + "', NOW(), '" + isPaid + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
