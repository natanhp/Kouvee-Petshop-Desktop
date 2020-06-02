package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.*;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceTransactionDetailDAO {
    //SELECT a Service transaction detail
    public static ObservableList<ServiceTransactionDetail> searchTransaction(String transactionDetailName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT  std.ID AS std_Id," +
                " st.id AS st_Id," +
                " emp.name AS emp_Name," +
                " st.date AS st_Date," +
                " p.name AS p_Name," +
                " s.serviceName AS s_Name," +
                " std.isFinished AS isFinished" +
                " st.isPaid AS isPaid," +
                " st.total AS st_Total" +
                " FROM servicetransactiondetail AS std" +
                " JOIN servicetransaction AS st ON st.id = std.ServiceTransaction_id" +
                " JOIN servicedetails AS sd ON std.ServiceDetails_id = sd.id" +
                " JOIN pets AS p ON st.Pets_id = p.id" +
                " JOIN employees AS emp ON st.Employees_id = emp.id" +
                " JOIN services AS s ON sd.Services_id = s.id" +
                " WHERE std.id = '" + transactionDetailName + "' AND (std.deletedAt IS NULL AND st.deletedAt IS NULL)";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsTransactionDetail = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<ServiceTransactionDetail> std = FXCollections.observableArrayList();
            try {
                std = getServiceTransactionDetailList(rsTransactionDetail);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return std;
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
    public static ObservableList<ServiceTransactionDetail> searchServiceTransactionDetails() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT std.ID AS std_Id," +
                " st.id AS st_Id," +
                " emp.name AS emp_Name," +
                " st.date AS st_Date," +
                " p.name AS p_Name," +
                " s.serviceName AS s_Name," +
                " std.isFinished AS isFinished," +
                " st.isPaid AS isPaid," +
                " st.total AS st_Total" +
                " FROM servicetransactiondetail AS std" +
                " JOIN servicetransaction AS st ON st.id = std.ServiceTransaction_id" +
                " JOIN servicedetails AS sd ON std.ServiceDetails_id = sd.id" +
                " JOIN pets AS p ON st.Pets_id = p.id" +
                " JOIN employees AS emp ON st.Employees_id = emp.id" +
                " JOIN services AS s ON sd.Services_id = s.id " +
                " WHERE std.deletedAt IS NULL AND st.deletedAt IS NULL AND std.isFinished = '0'";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsStds = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getServiceTransactionDetailList method and get std object

            //Return Std Object
            return getServiceTransactionDetailList(rsStds);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT ServiceTransactionDetails
    public static ObservableList<ServiceTransactionDetail> searchTransactions() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT std.ID AS std_Id," +
                " st.id AS st_Id," +
                " emp.name AS emp_Name," +
                " st.date AS st_Date," +
                " p.name AS p_Name," +
                " s.serviceName AS s_Name," +
                " std.isFinished AS isFinished," +
                " st.isPaid AS isPaid," +
                " sd.price AS sd_Price," +
                " st.total AS st_Total " +
                " FROM servicetransactiondetail AS std" +
                " JOIN servicetransaction AS st ON st.id = std.ServiceTransaction_id" +
                " JOIN servicedetails AS sd ON std.ServiceDetails_id = sd.id" +
                " JOIN pets AS p ON st.Pets_id = p.id" +
                " JOIN employees AS emp ON st.Employees_id = emp.id" +
                " JOIN services AS s ON sd.Services_id = s.id " +
                " WHERE std.deletedAt IS NULL AND st.deletedAt IS NULL AND st.isPaid = '0'";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsStds = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getServiceTransactionDetailList method and get std object

            //Return Std Object
            return getServiceTransactionsList(rsStds);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM Stds operation
    public static ObservableList<ServiceTransactionDetail> getServiceTransactionDetailList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Std Objects
        ObservableList<ServiceTransactionDetail> stdList = FXCollections.observableArrayList();

        while (rs.next()) {

            ServiceTransactionDetail std = new ServiceTransactionDetail(null);
            std = new ServiceTransactionDetail(null);
            std.setId(rs.getInt("std_Id"));
            std.setServiceTransaction_Id(rs.getString("st_Id"));
            std.setEmployees_Id(rs.getString("emp_Name"));
            std.setDate(rs.getDate("st_Date"));
            std.setPets_Id(rs.getString("p_Name"));
            std.setServiceId(rs.getString("s_Name"));
            byte[] target = rs.getBytes("isFinished");
            std.setIsPaid(rs.getBytes("isPaid"));
            std.setTotal(rs.getDouble("st_Total"));
//            std.setCreatedAt(rs.getTimestamp("createdAt"));
//            std.setUpdatedAt(rs.getTimestamp("updatedAt"));
//            std.setDeletedAt(rs.getTimestamp("deletedAt"));
//            std.setCreatedBy(rs.getString("Name Created"));
//            std.setUpdatedBy(rs.getString("Name Updated"));

            String string = new String(target);
            std.setIsFinished(string);
            //Add pet to the ObservableList
            stdList.add(std);

        }

        //Return stdList (ObservableList of Stds
        return stdList;
    }

    //SELECT * FROM Stds operation
    public static ObservableList<ServiceTransactionDetail> getServiceTransactionsList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Std Objects
        ObservableList<ServiceTransactionDetail> stdList = FXCollections.observableArrayList();

        while (rs.next()) {

            ServiceTransactionDetail std = new ServiceTransactionDetail(null);
            std = new ServiceTransactionDetail(null);
            std.setId(rs.getInt("std_Id"));
            std.setServiceTransaction_Id(rs.getString("st_Id"));
            std.setEmployees_Id(rs.getString("emp_Name"));
            std.setDate(rs.getDate("st_Date"));
            std.setPets_Id(rs.getString("p_Name"));
            std.setServiceId(rs.getString("s_Name"));
            byte[] target = rs.getBytes("isFinished");
            std.setIsPaid(rs.getBytes("isPaid"));
            std.setSubTotal(rs.getDouble("sd_Price"));
            std.setTotal(rs.getDouble("st_Total"));
//            std.setCreatedAt(rs.getTimestamp("createdAt"));
//            std.setUpdatedAt(rs.getTimestamp("updatedAt"));
//            std.setDeletedAt(rs.getTimestamp("deletedAt"));
//            std.setCreatedBy(rs.getString("Name Created"));
//            std.setUpdatedBy(rs.getString("Name Updated"));

            String string = new String(target);
            std.setIsFinished(string);
            //Add pet to the ObservableList
            stdList.add(std);

        }

        //Return stdList (ObservableList of Stds
        return stdList;
    }

    //Search the ID of the STD
    public static ServiceTransactionDetail searchID(String sdID, String stID) throws SQLException, ClassNotFoundException {

        //Declare SELECT statement
        String searchStmt = "SELECT id FROM Servicetransactiondetail " +
                "WHERE ServiceDetails_id = '" + sdID + "' AND ServiceTransaction_id = '" + stID +"'";

        //Execute query
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSearch = DBUtil.dbExecuteQuery(searchStmt);

            ServiceTransactionDetail std = getIDFromResultSet(rsSearch);

            return std;

        } catch (SQLException ex) {
            System.out.println("Error occurred while SELECT operation: " + ex);

            //throws exceptions
            throw ex;
        }
    }

    private static ServiceTransactionDetail getIDFromResultSet(ResultSet rs) throws SQLException {
        ServiceTransactionDetail std = null;

        if (rs.next()) {
            std = new ServiceTransactionDetail(null);
            std.setId(rs.getInt("id"));
        }
        return std;
    }

    //Update an Std's entries
    public static void updateEntries(String Logged, String Id, int isFinished, String ServiceDetails_Id,
                                     String ServiceTransaction_Id)
            throws SQLException, ClassNotFoundException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Servicetransactiondetail " +
                        " SET " +
                        "     isFinished = '" + isFinished + "' " +
                        "     , ServiceDetails_id = '" + ServiceDetails_Id + "' " +
                        "     , ServiceTransaction_id = '" + ServiceTransaction_Id + "' " +
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

    //DELETE a servicetransactiondetail
    public static void deleteTranWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM Servicetransactiondetail " +
                        "WHERE ServiceTransaction_id = '" + Id + "' AND isFinished = 0;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE a Servicetransactiondetail
    public static void softDeleteTranWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Servicetransactiondetail " +
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
    public static void insertStd(String Logged, int isFinished, String ServiceDetails_Id, String ServiceTransaction_id)
            throws SQLException {
        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Servicetransactiondetail " +
                        " (isFinished, ServiceDetails_id, ServiceTransaction_id, createdAt, createdBy) " +
                        "    VALUES " +
                        "    ('"+ isFinished + "', '"+ ServiceDetails_Id +"', '" + ServiceTransaction_id +"', NOW(), '" + Logged + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
