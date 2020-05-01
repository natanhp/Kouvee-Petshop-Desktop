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
        String selectStmt = "SELECT spr.idSupplier, spr.name AS 'Supplier Name', spr.address, spr.phoneNumber, " +
                "spr.createdAt, spr.updatedAt, spr.deletedAt, e.name AS 'Name Created', m.name AS 'Name Updated', " +
                "l.name AS 'Name Deleted' " +
                "FROM suppliers AS spr " +
                "LEFT JOIN employees AS e ON " +
                "e.id = spr.createdBy " +
                "LEFT JOIN employees AS m ON " +
                "m.id = spr.updatedBy " +
                "LEFT JOIN employees AS l ON " +
                "l.id = spr.deletedBy " +
                "WHERE spr.name LIKE '%" + sprName + "%' " +
                "AND spr.deletedAt IS NULL;";

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
            spr.setCreatedAt(rs.getTimestamp("createdAt"));
            spr.setUpdatedAt(rs.getTimestamp("updatedAt"));
            spr.setDeletedAt(rs.getTimestamp("deletedAt"));
            spr.setCreatedBy(rs.getString("Name Created"));
            spr.setUpdatedBy(rs.getString("Name Updated"));
            spr.setDeletedBy(rs.getString("Name Deleted"));
        }

        return spr;
    }

    //SELECT Suppliers
    public static ObservableList<Supplier> searchSuppliers() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT spr.idSupplier, spr.name AS 'Supplier Name', spr.address, spr.phoneNumber, " +
                "spr.createdAt, spr.updatedAt, spr.deletedAt, e.name AS 'Name Created', m.name AS 'Name Updated', " +
                "l.name AS 'Name Deleted' " +
                "FROM suppliers AS spr " +
                "LEFT JOIN employees AS e ON " +
                "e.id = spr.createdBy " +
                "LEFT JOIN employees AS m ON " +
                "m.id = spr.updatedBy " +
                "LEFT JOIN employees AS l ON " +
                "l.id = spr.deletedBy " +
                "WHERE spr.deletedAt IS NUll";

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
            spr.setName(rs.getString("Supplier Name"));
            spr.setPhoneNumber(rs.getString("phoneNumber"));
            spr.setAddress(rs.getString("address"));
            spr.setCreatedAt(rs.getTimestamp("createdAt"));
            spr.setUpdatedAt(rs.getTimestamp("updatedAt"));
            spr.setDeletedAt(rs.getTimestamp("deletedAt"));
            spr.setCreatedBy(rs.getString("Name Created"));
            spr.setUpdatedBy(rs.getString("Name Updated"));
            spr.setDeletedBy(rs.getString("Name Deleted"));

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
