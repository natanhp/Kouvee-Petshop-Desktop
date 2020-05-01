package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.PetType;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetTypeDAO {

    //SELECT a PetType
    public static ObservableList<PetType> searchPetType(String ptName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT pt.id AS 'id', pt.type AS 'type', pt.createdAt, pt.updatedAt, pt.deletedAt, " +
                "e.name AS 'Name Created', m.name AS 'Name Updated', l.name AS 'Name Deleted' " +
                "FROM PetTypes pt " +
                "LEFT JOIN employees e ON " +
                "e.id = pt.createdBy " +
                "LEFT JOIN employees m ON " +
                "m.id = pt.updatedBy " +
                "LEFT JOIN employees l ON " +
                "l.id = pt.deletedBy " +
                "WHERE pt.type LIKE '%" + ptName + "%' AND pt.deletedAt IS NULL;";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPt = DBUtil.dbExecuteQuery(selectStmt);
            
            return getPetTypeList(rsPt);
        } catch (SQLException ex) {
            System.out.println("While searching a pet type with Type : " + ptName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static PetType getPetTypesFromResultSet(ResultSet rs) throws SQLException {
        PetType pt = null;

        if (rs.next()) {
            pt = new PetType();
            pt.setId(rs.getInt("id"));
            pt.setType(rs.getString("type"));
        }

        return pt;
    }

    //SELECT PetTypes
    public static ObservableList<PetType> searchPetTypes() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT pt.id AS 'id', pt.type AS 'type', pt.createdAt, pt.updatedAt, pt.deletedAt, " +
                "e.name AS 'Name Created', m.name AS 'Name Updated', l.name AS 'Name Deleted' " +
                "FROM PetTypes pt " +
                "LEFT JOIN employees e ON " +
                "e.id = pt.createdBy " +
                "LEFT JOIN employees m ON " +
                "m.id = pt.updatedBy " +
                "LEFT JOIN employees l ON " +
                "l.id = pt.deletedBy " +
                "WHERE pt.deletedAt IS NUll";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPts = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getPetTypeList method and get pettype object
            ObservableList<PetType> ptList = getPetTypeList(rsPts);

            //Return PetType Object
            return ptList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM pettypes operation
    public static ObservableList<PetType> getPetTypeList(ResultSet rs) throws SQLException {

        //Declare a observable List which comprises of PetType Objects
        ObservableList<PetType> ptList = FXCollections.observableArrayList();

        while (rs.next()) {
            PetType pt = new PetType();
            pt = new PetType();
            pt.setId(rs.getInt("id"));
            pt.setType(rs.getString("type"));
            pt.setCreatedAt(rs.getTimestamp("createdAt"));
            pt.setUpdatedAt(rs.getTimestamp("updatedAt"));
            pt.setDeletedAt(rs.getTimestamp("deletedAt"));
            pt.setCreatedBy(rs.getString("Name Created"));
            pt.setUpdatedBy(rs.getString("Name Updated"));
            pt.setDeletedBy(rs.getString("Name Deleted"));

            //Add pettype to the ObservableList
            ptList.add(pt);
        }

        //Return ptList (ObservableList of PetTypes
        return ptList;
    }

    //Update an pettype's entries
    public static void updateEntries(String Logged, String Id, String type) throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE PetTypes " +
                        "SET type = '" + type + "' " +
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

    //DELETE a pettype
    public static void deletePtWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM PetTypes " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE a pettype
    public static void softDeletePtWithId(String Logged, String Id) throws SQLException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE PetTypes " +
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

    //INSERT a PetType
    public static void insertPt(String Logged, String type) throws SQLException {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO PetTypes " +
                        "(type, createdAt, createdBy)" +
                        "VALUES " +
                        "('" + type + "', " + "NOW(), " +
                        "'" + Logged + "');";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
