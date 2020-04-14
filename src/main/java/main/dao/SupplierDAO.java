package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Supplier;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAO {

    //SELECT an Supplier
    public static ObservableList<Supplier> searchSupplier(String sprName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM Suppliers WHERE name LIKE '%" + sprName + "%' AND deletedAt IS NULL;";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSpr = DBUtil.dbExecuteQuery(selectStmt);

            return getSupplierList(rsSpr);
        } catch (SQLException ex) {
            System.out.println("While searching an supplier with Name : " + sprName + ", an error occured: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Supplier getSupplierFromResultSet(ResultSet rs) throws SQLException {
        Supplier spr = null;

        if (rs.next()) {
            spr = new Supplier();
            spr.setId(rs.getInt("idSupplier"));
            spr.setName(rs.getString("name"));
            spr.setPhoneNumber(rs.getString("phoneNumber"));
            spr.setAddress(rs.getString("address"));
        }

        return spr;
    }

    //SELECT Suppliers
    public static ObservableList<Supplier> searchSuppliers() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Suppliers WHERE deletedAt IS NULL";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSprs = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getSupplierList method and get supplier object

            //Return Supplier Object
            return getSupplierList(rsSprs);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM suppliers operation
    public static ObservableList<Supplier> getSupplierList(ResultSet rs) throws SQLException {

        //Declare a observable List which comprises of Supplier Objects
        ObservableList<Supplier> sprList = FXCollections.observableArrayList();

        while (rs.next()) {
            Supplier spr = new Supplier();
            spr = new Supplier();
            spr.setId(rs.getInt("idSupplier"));
            spr.setName(rs.getString("name"));
            spr.setPhoneNumber(rs.getString("phoneNumber"));
            spr.setAddress(rs.getString("address"));

            //Add supplier to the ObservableList
            sprList.add(spr);
        }

        //Return sprList (ObservableList of Suppliers
        return sprList;
    }

    //Update an supplier's entries
    public static void updateEntries(String Logged, String Id, String name, String address, String phoneNumber)
            throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Suppliers " +
                        "SET name = '" + name + "' " +
                        ", address = '" + address + "' " +
                        ", phoneNumber = '" + phoneNumber + "' " +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        "WHERE idSupplier = '" + Id + "' AND deletedAt IS NULL;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE an supplier
    public static void deleteSprWithId(String Id) throws SQLException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM Suppliers " +
                        "WHERE idSupplier = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE an supplier
    public static void softDeleteSprWithId(String Logged, String Id) throws SQLException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Suppliers " +
                        "SET " +
                        "deletedAt = NOW()" +
                        ", deletedBy = " + Logged +
                        " WHERE idSupplier = " + Id + " AND deletedAt IS NULL;";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " + ex);
            throw ex;
        }
    }

    //INSERT an Supplier
    public static void insertSpr(String Logged, String name, String address,
                                 String phoneNumber)
            throws SQLException, ClassNotFoundException {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Suppliers " +
                        "(name, address, phoneNumber, createdAt, createdBy)" +
                        " VALUES " +
                        "('" + name + "','" + address + "','" + phoneNumber +
                        "', NOW(),'" +
                        Logged + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
