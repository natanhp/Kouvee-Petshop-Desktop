package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.ProductTransactionDetail;
import main.model.ServiceTransactionDetail;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTransactionDetailDAO {
    //SELECT a Producttransaction detail
    public static ObservableList<ProductTransactionDetail> searchTransaction(String transactionDetailName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT  ptd.ID AS ptd_Id, " +
                "                pt.id AS pt_Id, " +
                "        emp.name AS emp_Name, " +
                "                c.name AS c_Name, " +
                "        p.productName AS p_Name, " +
                "                pt.isPaid AS isPaid, " +
                "        pt.total AS pt_Total " +
                "        FROM Producttransactiondetail AS ptd " +
                "        JOIN Producttransaction AS pt ON pt.id = ptd.ProductTransaction_id " +
                "        JOIN Products AS p ON ptd.Products_id = p.id " +
                "        JOIN Customers AS c ON pt.Customers_id = c.id " +
                "        JOIN employees AS emp ON pt.Employees_id = emp.id " +
                "        WHERE ptd.id = '" + transactionDetailName + "' AND (ptd.deletedAt IS NULL AND pt.deletedAt IS NULL)";



        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTransactionDetail = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<ProductTransactionDetail> ptd = FXCollections.observableArrayList();
            try {
                ptd = getProductTransactionDetailList(rsTransactionDetail);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return ptd;
        } catch (SQLException ex) {
            System.out.println("While searching a Service transaction detail with Id : " + transactionDetailName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static ServiceTransactionDetail getServiceTransactionDetailFromResultSet(ResultSet rs) throws SQLException {
        ServiceTransactionDetail std = null;

        if (rs.next()) {
            std = new ServiceTransactionDetail(null);
            std.setId(rs.getInt("st_Id"));
            std.setServiceTransaction_Id(rs.getString("std_Id"));
            std.setDate(rs.getDate("st_Date"));
            std.setPets_Id(rs.getString("p_Name"));
            std.setServiceId(rs.getString("s_Name"));
            std.setIsFinished(rs.getString("isFinished"));
            std.setIsPaid(rs.getBytes("isPaid"));
            std.setTotal(rs.getDouble("st_Total"));
        }
        return std;
    }

    //SELECT ServiceTransactionDetails
    public static ObservableList<ProductTransactionDetail> searchProductTransactionDetails() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT  ptd.ID AS ptd_Id, " +
                "                pt.id AS pt_Id, " +
                "        emp.name AS emp_Name, " +
                "                c.name AS c_Name, " +
                "        p.productName AS p_Name, " +
                "                pt.isPaid AS isPaid, " +
                "        pt.total AS pt_Total " +
                "        FROM Producttransactiondetail AS ptd " +
                "        JOIN Producttransaction AS pt ON pt.id = ptd.ProductTransaction_id " +
                "        JOIN Products AS p ON ptd.Products_id = p.id " +
                "        JOIN Customers AS c ON pt.Customers_id = c.id " +
                "        JOIN employees AS emp ON pt.Employees_id = emp.id " +
                "        WHERE ptd.deletedAt IS NULL AND pt.deletedAt IS NULL";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPtds = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getServiceTransactionDetailList method and get ptd object

            //Return Std Object
            return getProductTransactionDetailList(rsPtds);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT ServiceTransactionDetails
    public static ObservableList<ProductTransactionDetail> searchTransactions() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT ptd.ID AS ptd_Id," +
                " pt.id AS pt_Id," +
                " emp.name AS emp_Name," +
                " c.name AS c_Name," +
                " p.productName AS p_Name," +
                " pt.isPaid AS isPaid," +
                " p.price AS p_Price," +
                " pt.total AS pt_Total " +
                " FROM Producttransactiondetail AS ptd" +
                " JOIN Producttransaction AS pt ON pt.id = ptd.ProductTransaction_id" +
                " JOIN Products AS p ON ptd.Products_id = p.id" +
                " JOIN Customers AS c ON pt.Customers_id = c.id" +
                " JOIN employees AS emp ON st.Employees_id = emp.id" +
                " WHERE ptd.deletedAt IS NULL AND pt.deletedAt IS NULL AND pt.isPaid = '0'";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPtds = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getServiceTransactionDetailList method and get std object

            //Return Ptd Object
            return getProductTransactionsList(rsPtds);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM Stds operation
    public static ObservableList<ProductTransactionDetail> getProductTransactionDetailList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Std Objects
        ObservableList<ProductTransactionDetail> ptdList = FXCollections.observableArrayList();

        while (rs.next()) {

            ProductTransactionDetail ptd = new ProductTransactionDetail(null);
            ptd = new ProductTransactionDetail(null);
            ptd.setId(rs.getInt("ptd_Id"));
            ptd.setProductTransaction_Id(rs.getString("pt_Id"));
            ptd.setEmployees_Id(rs.getString("emp_Name"));
            ptd.setCustomers_Id(rs.getString("c_Name"));
            ptd.setProductName(rs.getString("p_Name"));
            ptd.setIsPaid(rs.getBytes("isPaid"));
            ptd.setTotal(rs.getDouble("pt_Total"));
//            std.setCreatedAt(rs.getTimestamp("createdAt"));
//            std.setUpdatedAt(rs.getTimestamp("updatedAt"));
//            std.setDeletedAt(rs.getTimestamp("deletedAt"));
//            std.setCreatedBy(rs.getString("Name Created"));
//            std.setUpdatedBy(rs.getString("Name Updated"));

            //Add pet to the ObservableList
            ptdList.add(ptd);

        }

        //Return stdList (ObservableList of Stds
        return ptdList;
    }

    //SELECT * FROM Stds operation
    public static ObservableList<ProductTransactionDetail> getProductTransactionsList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Std Objects
        ObservableList<ProductTransactionDetail> ptdList = FXCollections.observableArrayList();

        while (rs.next()) {

            ProductTransactionDetail ptd = new ProductTransactionDetail(null);
            ptd = new ProductTransactionDetail(null);
            ptd.setId(rs.getInt("ptd_Id"));
            ptd.setProductTransaction_Id(rs.getString("pt_Id"));
            ptd.setEmployees_Id(rs.getString("emp_Name"));
            ptd.setProductName(rs.getString("p_Name"));
            ptd.setIsPaid(rs.getBytes("isPaid"));
            ptd.setProductPrice(rs.getDouble("p_Price"));
            ptd.setTotal(rs.getDouble("pt_Total"));
//            std.setCreatedAt(rs.getTimestamp("createdAt"));
//            std.setUpdatedAt(rs.getTimestamp("updatedAt"));
//            std.setDeletedAt(rs.getTimestamp("deletedAt"));
//            std.setCreatedBy(rs.getString("Name Created"));
//            std.setUpdatedBy(rs.getString("Name Updated"));

            //Add pet to the ObservableList
            ptdList.add(ptd);

        }

        //Return stdList (ObservableList of Ptds
        return ptdList;
    }

    //Search the ID of the PTD
    public static ProductTransactionDetail searchID(String pID, String ptID) throws SQLException, ClassNotFoundException {

        //Declare SELECT statement
        String searchStmt = "SELECT id FROM Producttransactiondetail " +
                "WHERE Products_id = '" + pID + "' AND ProductTransaction_id = '" + ptID +"'";

        //Execute query
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSearch = DBUtil.dbExecuteQuery(searchStmt);

            ProductTransactionDetail ptd = getIDFromResultSet(rsSearch);

            return ptd;

        } catch (SQLException ex) {
            System.out.println("Error occurred while SELECT operation: " + ex);

            //throws exceptions
            throw ex;
        }
    }

    private static ProductTransactionDetail getIDFromResultSet(ResultSet rs) throws SQLException {
        ProductTransactionDetail ptd = null;

        if (rs.next()) {
            ptd = new ProductTransactionDetail(null);
            ptd.setId(rs.getInt("id"));
        }
        return ptd;
    }

    //Update an Std's entries
    public static void updateEntries(String Logged, String Id, String Products_Id,
                                     String ProductTransaction_Id)
            throws SQLException, ClassNotFoundException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE ProductTransactionDetail " +
                        " SET " +
                        "     , Products_id = '" + Products_Id + "' " +
                        "     , ProductTransaction_id = '" + ProductTransaction_Id + "' " +
                        "     , updatedAt = NOW() " +
                        "     , updatedBy = '" + Logged + "' " +
                        "    WHERE id = '"+ Id + "'";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE a producttransactiondetail
    public static void deleteTranWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM Producttransactiondetail " +
                        "WHERE ProductTransaction_id = '" + Id + "'";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE a Producttransactiondetail
    public static void softDeleteTranWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Producttransactiondetail " +
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

    //INSERT a Std
    public static void insertPtd(String Logged, String Products_Id, String ProductTransaction_id)
            throws SQLException {
        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Producttransactiondetail " +
                        " (Products_id, ProductTransaction_id, createdAt, createdBy) " +
                        "    VALUES " +
                        "    ('"+ Products_Id + "', '"+ ProductTransaction_id +"', NOW(), '" + Logged + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
