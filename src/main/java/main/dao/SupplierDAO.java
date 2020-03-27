package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Supplier;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAO {

    //SELECT an Supplier
    public static Supplier searchSupplier(String sprId) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM suppliers WHERE idSupplier =" + sprId;

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSpr = DBUtil.dbExecuteQuery(selectStmt);

            Supplier supplier = getSupplierFromResultSet(rsSpr);

            return supplier;
        } catch (SQLException ex) {
            System.out.println("While searching an supplier with Id : " + sprId + ", an error occured: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Supplier getSupplierFromResultSet(ResultSet rs) throws SQLException {
        Supplier spr = null;

        if(rs.next()) {
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
        String selectStmt = "SELECT * FROM suppliers";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSprs = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getSupplierList method and get supplier object
            ObservableList<Supplier> sprList = getSupplierList(rsSprs);

            //Return Supplier Object
            return sprList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM suppliers operation
    public static ObservableList<Supplier> getSupplierList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Supplier Objects
        ObservableList<Supplier> sprList = FXCollections.observableArrayList();

        while(rs.next()) {
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
    public static void updateEntries (String Logged, String Id, String name, String address, String phoneNumber)
            throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE suppliers " +
                        "SET name = '" + name + "' " +
                        ", address = '" + address + "' " +
                        ", phoneNumber = '" + phoneNumber + "' " +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        "WHERE idSupplier = '" + Id + "';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE an supplier
    public static void deleteSprWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM suppliers " +
                        "WHERE idSupplier = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //SOFT DELETE an supplier
    public static void softDeleteSprWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE suppliers " +
                        "SET name = NULL" +
                        ", address = NULL" +
                        ", phoneNumber = NULL" +
                        ", deletedAt = NOW()" +
                        ", deletedBy " + Logged +
                        "WHERE idSupplier = '" + Id + "';";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " +ex);
            throw ex;
        }
    }

    //INSERT an Supplier
    public static void insertSpr(String Logged, String name, String address,
                                 String phoneNumber)
            throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO suppliers " +
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
