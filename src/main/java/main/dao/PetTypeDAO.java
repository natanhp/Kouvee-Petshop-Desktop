package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.PetType;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetTypeDAO {

    //SELECT a PetType
    public static PetType searchPetType(String ptId) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM  pettypes WHERE Id =" + ptId;

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPt = DBUtil.dbExecuteQuery(selectStmt);

            PetType petType  = getPetTypesFromResultSet(rsPt);

            return petType;
        } catch (SQLException ex) {
            System.out.println("While searching a pet type with Id : " + ptId + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static PetType getPetTypesFromResultSet(ResultSet rs) throws SQLException {
        PetType pt = null;

        if(rs.next()) {
            pt = new PetType();
            pt.setId(rs.getInt("id"));
            pt.setType(rs.getString("type"));
        }

        return pt;
    }

    //SELECT PetTypes
    public static ObservableList<PetType> searchPetTypes() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM pettypes";

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
            throw ex ;
        }
    }

    //SELECT * FROM pettypes operation
    public static ObservableList<PetType> getPetTypeList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of PetType Objects
        ObservableList<PetType> ptList = FXCollections.observableArrayList();

        while(rs.next()) {
            PetType pt = new PetType();
            pt = new PetType();
            pt.setId(rs.getInt("id"));
            pt.setType(rs.getString("type"));

            //Add pettype to the ObservableList
            ptList.add(pt);
        }

        //Return ptList (ObservableList of PetTypes
        return ptList;
    }

    //Update an pettype's entries
    public static void updateEntries (String Logged, String Id, String type) throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE pettypes " +
                        "SET type = '" + type + "' " +
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

    //DELETE a pettype
    public static void deletePtWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM pettypes " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //SOFT DELETE a pettype
    public static void softDeletePtWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE pettypes " +
                        "SET type = NULL" +
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

    //INSERT a PetType
    public static void insertPt(String Logged, String type) throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO pettypes " +
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
