package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.PetSize;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetSizeDAO {

    //SELECT a PetSize
    public static ObservableList<PetSize> searchPetSize(String psName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT ps.id AS 'id', ps.size AS 'size', ps.createdAt, ps.updatedAt, ps.deletedAt, " +
                "e.name AS 'Name Created', m.name AS 'Name Updated', l.name AS 'Name Deleted' " +
                "FROM Petsizes AS ps " +
                "LEFT JOIN employees AS e ON " +
                "e.id = ps.createdBy " +
                "LEFT JOIN employees AS m ON " +
                "m.id = ps.updatedBy " +
                "LEFT JOIN employees AS l ON " +
                "l.id = ps.deletedBy " +
                "WHERE ps.size LIKE '%" + psName + "%' AND ps.deletedAt IS NULL;";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPs = DBUtil.dbExecuteQuery(selectStmt);

            return getPetSizeList(rsPs);
        } catch (SQLException ex) {
            System.out.println("While searching a pet size with Size : " + psName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static PetSize getPetSizesFromResultSet(ResultSet rs) throws SQLException {
        PetSize ps = null;

        if (rs.next()) {
            ps = new PetSize();
            ps.setId(rs.getInt("id"));
            ps.setSize(rs.getString("size"));
        }

        return ps;
    }

    //SELECT PetSizes
    public static ObservableList<PetSize> searchPetSizes() throws SQLException {

        //Declare a SELECT statement
        String selectStmt = "SELECT ps.id AS 'id', ps.size AS 'size', ps.createdAt, ps.updatedAt, ps.deletedAt, " +
                "e.name AS 'Name Created', m.name AS 'Name Updated', l.name AS 'Name Deleted' " +
                "FROM Petsizes ps " +
                "LEFT JOIN employees e ON " +
                "e.id = ps.createdBy " +
                "LEFT JOIN employees m ON " +
                "m.id = ps.updatedBy " +
                "LEFT JOIN employees l ON " +
                "l.id = ps.deletedBy " +
                "WHERE ps.deletedAt IS NUll";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPss = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getPetSizeList method and get petsize object

            //Return PetSize Object
            return getPetSizeList(rsPss);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM petsizes operation
    public static ObservableList<PetSize> getPetSizeList(ResultSet rs) throws SQLException {

        //Declare a observable List which comprises of PetSize Objects
        ObservableList<PetSize> psList = FXCollections.observableArrayList();

        while (rs.next()) {
            PetSize ps = new PetSize();
            ps = new PetSize();
            ps.setId(rs.getInt("id"));
            ps.setSize(rs.getString("size"));
            ps.setCreatedAt(rs.getTimestamp("createdAt"));
            ps.setUpdatedAt(rs.getTimestamp("updatedAt"));
            ps.setDeletedAt(rs.getTimestamp("deletedAt"));
            ps.setCreatedBy(rs.getString("Name Created"));
            ps.setUpdatedBy(rs.getString("Name Updated"));
            ps.setDeletedBy(rs.getString("Name Deleted"));

            //Add petsize to the ObservableList
            psList.add(ps);
        }

        //Return psList (ObservableList of PetSizes
        return psList;
    }

    //Update an petsize's entries
    public static void updateEntries(String Logged, String Id, String size) throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE PetSizes " +
                        "SET size = '" + size + "' " +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        "WHERE id = '" + Id + "' AND deletedAt IS NULL;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE a petsize
    public static void deletePsWithId(String Id) throws SQLException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM PetSizes " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE a petsize
    public static void softDeletePsWithId(String Logged, String Id) throws SQLException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE PetSizes " +
                        "SET deletedAt = NOW()" +
                        ", deletedBy = " + Logged +
                        " WHERE id = " + Id + " AND deletedAt IS NULL;";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " + ex);
            throw ex;
        }
    }

    //INSERT a PetSize
    public static void insertPs(String Logged, String size) throws SQLException {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO PetSizes " +
                        "(size, createdAt, createdBy)" +
                        "VALUES " +
                        "('" + size + "', " + "NOW(), " +
                        "'" + Logged + "');";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
