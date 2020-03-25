package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Customer;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    //SELECT a Customer
    public static Customer searchCustomer(String cusId) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM customers WHERE Id =" + cusId;

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsCus = DBUtil.dbExecuteQuery(selectStmt);

            Customer customer = getCustomersFromResultSet(rsCus);

            return customer;
        } catch (SQLException ex) {
            System.out.println("While searching a customer with Id : " + cusId + ", an error occured: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Customer getCustomersFromResultSet(ResultSet rs) throws SQLException {
        Customer cus = null;

        if(rs.next()) {
            cus = new Customer();
            cus.setId(rs.getInt("id"));
            cus.setName(rs.getString("name"));
            cus.setDateBirth(rs.getDate("dateBirth"));
            cus.setPhoneNumber(rs.getString("phoneNumber"));
            cus.setAddress(rs.getString("address"));
        }

        return cus;
    }

    //SELECT Customers
    public static ObservableList<Customer> searchCustomers() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM customers";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsCuss = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getCustomerList method and get customer object
            ObservableList<Customer> cusList = getCustomerList(rsCuss);

            //Return Customer Object
            return cusList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM customers operation
    public static ObservableList<Customer> getCustomerList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Customer Objects
        ObservableList<Customer> cusList = FXCollections.observableArrayList();

        while(rs.next()) {
            Customer cus = new Customer();
            cus = new Customer();
            cus.setId(rs.getInt("id"));
            cus.setName(rs.getString("name"));
            cus.setDateBirth(rs.getDate("dateBirth"));
            cus.setAddress(rs.getString("address"));
            cus.setPhoneNumber(rs.getString("phoneNumber"));

            //Add customer to the ObservableList
            cusList.add(cus);
        }

        //Return cusList (ObservableList of Customers
        return cusList;
    }

    //Update an customer's entries
    public static void updateEntries (String Logged, String Id, String name, String dateBirth, String address,
                                      String phoneNumber)
            throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE customers " +
                        "SET name = '" + name + "' " +
                        ", address = '" + address + "' " +
                        ", dateBirth = '" + dateBirth + "' " +
                        ", phoneNumber = '" + phoneNumber + "' " +
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

    //DELETE a customer
    public static void deleteCusWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM customers " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //SOFT DELETE a customer
    public static void softDeleteCusWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE customers " +
                        "SET name = NULL" +
                        ", address = NULL" +
                        ", dateBirth = NULL" +
                        ", phoneNumber = NULL" +
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
    public static void insertCus(String Logged, String name, String dateBirth, String address,
                                 String phoneNumber)
            throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO customers " +
                        "(name, address, dateBirth, phoneNumber, createdAt, createdBy)" +
                        "VALUES " +
                        "('" + name + "','" + address + "','" + dateBirth + "','" + phoneNumber + "', NOW()," +
                        "'" + Logged + "');";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
