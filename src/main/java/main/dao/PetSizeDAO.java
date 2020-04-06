package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.PetSize;
import main.model.PetSize;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetSizeDAO {

    //SELECT a PetSize
    public static PetSize searchPetSize(String psName) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM  petsizes WHERE size = '" + psName + "';";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPs = DBUtil.dbExecuteQuery(selectStmt);

            PetSize petSize  = getPetSizesFromResultSet(rsPs);

            return petSize;
        } catch (SQLException ex) {
            System.out.println("While searching a pet size with Size : " + psName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static PetSize getPetSizesFromResultSet(ResultSet rs) throws SQLException {
        PetSize ps = null;

        if(rs.next()) {
            ps = new PetSize();
            ps.setId(rs.getInt("id"));
            ps.setSize(rs.getString("size"));
        }

        return ps;
    }

    //SELECT PetSizes
    public static ObservableList<PetSize> searchPetSizes() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM petsizes";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPss = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getPetSizeList method and get petsize object
            ObservableList<PetSize> psList = getPetSizeList(rsPss);

            //Return PetSize Object
            return psList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM petsizes operation
    public static ObservableList<PetSize> getPetSizeList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of PetSize Objects
        ObservableList<PetSize> psList = FXCollections.observableArrayList();

        while(rs.next()) {
            PetSize ps = new PetSize();
            ps = new PetSize();
            ps.setId(rs.getInt("id"));
            ps.setSize(rs.getString("size"));

            //Add petsize to the ObservableList
            psList.add(ps);
        }

        //Return psList (ObservableList of PetSizes
        return psList;
    }

    //Update an petsize's entries
    public static void updateEntries (String Logged, String Id, String size) throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE petsizes " +
                        "SET size = '" + size + "' " +
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

    //DELETE a petsize
    public static void deletePsWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM petsizes " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //SOFT DELETE a petsize
    public static void softDeletePsWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE petsizes " +
                        "SET size = NULL" +
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

    //INSERT a PetSize
    public static void insertPs(String Logged, String size) throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO petsizes " +
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
