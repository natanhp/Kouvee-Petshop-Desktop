package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Customer;
import main.model.Pet;
import main.model.ServiceTransaction;
import main.model.ServiceTransactionDetail;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceTransactionDetailDAO {
    //SELECT a Service transaction detail
    public static ObservableList<ServiceTransactionDetail> searchTransaction(String transactionDetailName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT  std.ID AS std_Id," +
                " st.id AS st_Id," +
                " temp.name AS emp_Name," +
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
                " WHERE std.id = '" + transactionDetailName + "'";

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
                " JOIN services AS s ON sd.Services_id = s.id";

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

    //SELECT * FROM pets operation
    public static ObservableList<ServiceTransactionDetail> getServiceTransactionDetailList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Pet Objects
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
            System.out.println("TARGET : " + string);
            //Add pet to the ObservableList
            stdList.add(std);

        }

        //Return petList (ObservableList of Pets
        return stdList;
    }

    //Update an pet's entries
    public static void updateEntries(String Logged, String Id, String name, String dateBirth, String Customers_id,
                                     String PetTypes_id, String PetSizes_id)
            throws SQLException, ClassNotFoundException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Pets " +
                        "SET name = '" + name + "' " +
                        ", dateBirth = '" + dateBirth + "' " +
                        ", Customers_id = '" + Customers_id + "' " +
                        ", PetTypes_id = '" + PetTypes_id + "' " +
                        ", PetSizes_id = '" + PetSizes_id + "' " +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        "WHERE id = '" + Id + "';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE an pet
    public static void deletePetWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM Pets " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE an pet
    public static void softDeletePetWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Pets " +
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

    //Search the Owner of the pet
    public static Customer searchOwner(String Customers_name) throws SQLException, ClassNotFoundException {

        //Declare INSERT statement
        String searchStmt = "SELECT id FROM Customers WHERE name = '" + Customers_name + "'";

        //Execute query
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSearch = DBUtil.dbExecuteQuery(searchStmt);

            Customer customer = getOwnerFromResultSet(rsSearch);

            return customer;

        } catch (SQLException ex) {
            System.out.println("Error occurred while SELECT operation: " + ex);

            //throws exceptions
            throw ex;
        }
    }

    private static Customer getOwnerFromResultSet(ResultSet rs) throws SQLException {
        Customer cs = null;

        if (rs.next()) {
            cs = new Customer();
            cs.setId(rs.getInt("id"));
        }
        return cs;
    }

    //INSERT a Pet
    public static void insertPet(String Logged, String name, String dateBirth, String Customers_id,
                                 String PetTypes_id, String PetSizes_id)
            throws SQLException {
        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Pets " +
                        "(name, dateBirth, createdAt, Customers_id, PetTypes_id, PetSizes_id, createdBy)" +
                        "VALUES " +
                        "('" + name + "','" + dateBirth +
                        "', NOW()," +
                        "'" + Customers_id + "','" + PetTypes_id + "','" + PetSizes_id + "','" + Logged + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
