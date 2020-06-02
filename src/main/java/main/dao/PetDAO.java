package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Customer;
import main.model.Pet;
import main.model.PetSize;
import main.model.PetType;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetDAO {

    //SELECT a Pet
    public static ObservableList<Pet> searchPet(String petName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT p.id AS 'id', p.name AS 'name', p.dateBirth AS 'dateBirth', " +
                "cs.name AS 'owner' , pt.type AS 'type', ps.size AS 'size', " +
                "p.createdAt, p.updatedAt, p.deletedAt, " +
                "e.name AS 'Name Created', m.name AS 'Name Updated', l.name AS 'Name Deleted'  " +
                "FROM pets AS p " +
                "JOIN Customers AS cs ON cs.id = p.Customers_id " +
                "JOIN PetTypes AS pt ON pt.id = p.PetTypes_id " +
                "JOIN PetSizes AS ps ON ps.id = p.PetSizes_id " +
                "LEFT JOIN employees AS e ON " +
                "e.id = p.createdBy " +
                "LEFT JOIN employees AS m ON " +
                "m.id = p.updatedBy " +
                "LEFT JOIN employees AS l ON " +
                "l.id = p.deletedBy " +
                "WHERE p.name LIKE '%" + petName + "%' AND (p.deletedAt IS NULL AND cs.deletedAt IS NULL AND ps.deletedAt IS NULL);";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPet = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Pet> pet = FXCollections.observableArrayList();
            try {
                pet = getPetList(rsPet);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return pet;
        } catch (SQLException ex) {
            System.out.println("While searching an pet with Id : " + petName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Pet getPetFromResultSet(ResultSet rs) throws SQLException {
        Pet pet = null;

        if (rs.next()) {
            pet = new Pet();
            pet.setId(rs.getInt("id"));
            pet.setName(rs.getString("name"));
            pet.setDateBirth(rs.getDate("dateBirth"));
            pet.setCustomer_name(rs.getString("owner"));
            pet.setPetSize_name(rs.getString("size"));
            pet.setPetType_name(rs.getString("type"));
        }
        return pet;
    }

    //SELECT Pets
    public static ObservableList<Pet> searchPets() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT p.id AS 'id', p.name AS 'name', p.dateBirth AS 'dateBirth', " +
                "cs.name AS 'owner' , pt.type AS 'type', ps.size AS 'size', " +
                "p.createdAt, p.updatedAt, p.deletedAt, " +
                "e.name AS 'Name Created', m.name AS 'Name Updated', l.name AS 'Name Deleted'  " +
                "FROM pets AS p " +
                "JOIN Customers AS cs ON cs.id = p.Customers_id " +
                "JOIN PetTypes AS pt ON pt.id = p.PetTypes_id " +
                "JOIN PetSizes AS ps ON ps.id = p.PetSizes_id " +
                "LEFT JOIN employees AS e ON " +
                "e.id = p.createdBy " +
                "LEFT JOIN employees AS m ON " +
                "m.id = p.updatedBy " +
                "LEFT JOIN employees AS l ON " +
                "l.id = p.deletedBy " +
                "WHERE (p.deletedAt IS NULL AND cs.deletedAt IS NULL AND ps.deletedAt IS NULL);";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPets = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getPetList method and get pet object

            //Return Pet Object
            return getPetList(rsPets);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM pets operation
    public static ObservableList<Pet> getPetList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Pet Objects
        ObservableList<Pet> petList = FXCollections.observableArrayList();

        while (rs.next()) {

            Pet pet = new Pet();
            pet = new Pet();
            pet.setId(rs.getInt("id"));
            pet.setName(rs.getString("name"));
            pet.setDateBirth(rs.getDate("dateBirth"));
            pet.setCustomer_name(rs.getString("owner"));
            pet.setPetType_name(rs.getString("type"));
            pet.setPetSize_name(rs.getString("size"));
            pet.setCreatedAt(rs.getTimestamp("createdAt"));
            pet.setUpdatedAt(rs.getTimestamp("updatedAt"));
            pet.setDeletedAt(rs.getTimestamp("deletedAt"));
            pet.setCreatedBy(rs.getString("Name Created"));
            pet.setUpdatedBy(rs.getString("Name Updated"));
            pet.setDeletedBy(rs.getString("Name Deleted"));

            //Add pet to the ObservableList
            petList.add(pet);

        }

        //Return petList (ObservableList of Pets
        return petList;
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

    //Search the Size of the pet
    public static PetSize searchSize(String Sizes_name) throws SQLException, ClassNotFoundException {

        //Declare SELECT statement
        String searchStmt = "SELECT id FROM Petsizes WHERE size = '" + Sizes_name + "'";

        //Execute query
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSearch = DBUtil.dbExecuteQuery(searchStmt);

            PetSize ps = getSizeFromResultSet(rsSearch);

            return ps;

        } catch (SQLException ex) {
            System.out.println("Error occurred while SELECT operation: " + ex);

            //throws exceptions
            throw ex;
        }
    }

    private static PetSize getSizeFromResultSet(ResultSet rs) throws SQLException {
        PetSize ps = null;

        if (rs.next()) {
            ps = new PetSize();
            ps.setId(rs.getInt("id"));
        }
        return ps;
    }

    //Search the Type of the pet
    public static PetType searchType(String Types_name) throws SQLException, ClassNotFoundException {

        //Declare INSERT statement
        String searchStmt = "SELECT id FROM Pettypes WHERE type = '" + Types_name + "'";

        //Execute query
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsSearch = DBUtil.dbExecuteQuery(searchStmt);

            PetType pt = getTypeFromResultSet(rsSearch);

            return pt;

        } catch (SQLException ex) {
            System.out.println("Error occurred while SELECT operation: " + ex);

            //throws exceptions
            throw ex;
        }
    }

    private static PetType getTypeFromResultSet(ResultSet rs) throws SQLException {
        PetType pt = null;

        if (rs.next()) {
            pt = new PetType();
            pt.setId(rs.getInt("id"));
        }
        return pt;
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
