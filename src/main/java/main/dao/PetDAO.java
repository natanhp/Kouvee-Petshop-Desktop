package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Customer;
import main.model.Pet;
import main.model.PetSize;
import main.model.PetType;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PetDAO {

    //SELECT a Pet
    public static Pet searchPet(String petId) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT p.id AS 'id', p.name AS 'name', p.dateBirth AS 'dateBirth', cs.customers_name AS 'owner', pt.type 'type', ps.size 'size' " +
                "FROM pets p " +
                "JOIN customers cs ON cs.id = p.id " +
                "JOIN pettypes pt ON pt.id = p.PetTypes_id " +
                "JOIN petsizes ps ON ps.id = p.PetSizes_id " +
                "WHERE p.id = " + petId +";";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPet = DBUtil.dbExecuteQuery(selectStmt);

            Pet pet = getPetFromResultSet(rsPet);

            return pet;
        } catch (SQLException ex) {
            System.out.println("While searching an pet with Id : " + petId + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Pet getPetFromResultSet(ResultSet rs) throws SQLException {
        Pet pet = null;

        if(rs.next()) {
            pet = new Pet();
            pet.setId(rs.getInt("id"));
            pet.setName(rs.getString("name"));
            pet.setDateBirth(rs.getDate("dateBirth"));
            pet.setCustomer_name(rs.getString("customers_name"));
            pet.setPetSize_name(rs.getString("size"));
            pet.setPetType_name(rs.getString("type"));
        }
        return pet;
    }

    //SELECT Pets
    public static ObservableList<Pet> searchPets() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT p.id AS 'id', p.name AS 'name', p.dateBirth AS 'dateBirth', cs.customers_name AS 'owner' , pt.type AS 'type', ps.size AS 'size' " +
                "FROM pets AS p " +
                "JOIN customers AS cs ON cs.id = p.id " +
                "JOIN pettypes AS pt ON pt.id = p.PetTypes_id " +
                "JOIN petsizes AS ps ON ps.id = p.PetSizes_id;";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPets = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getPetList method and get pet object
            ObservableList<Pet> petList = getPetList(rsPets);

            //Return Pet Object
            return petList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM pets operation
    public static ObservableList<Pet> getPetList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Pet Objects
        ObservableList<Pet> petList = FXCollections.observableArrayList();

        while(rs.next()) {

            Pet pet = new Pet();
            pet = new Pet();
            pet.setId(rs.getInt("id"));
            pet.setName(rs.getString("name"));
            pet.setDateBirth(rs.getDate("dateBirth"));
            pet.setCustomer_name(rs.getString("customers_name"));
            pet.setPetType_name(rs.getString("type"));
            pet.setPetSize_name(rs.getString("size"));

            //Add pet to the ObservableList
            petList.add(pet);

        }

        //Return petList (ObservableList of Pets
        return petList;
    }

    //Update an pet's entries
    public static void updateEntries (String Logged, String Id, String name, String dateBirth, String Customers_id,
                                      String PetTypes_id, String PetSizes_id)
            throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE pets " +
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
                "DELETE FROM pets " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //SOFT DELETE an pet
    public static void softDeletePetWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE pets " +
                        "SET name = NULL" +
                        ", dateBirth = NULL" +
                        ", Customers_id = NULL" +
                        ", PetTypes_id = NULL" +
                        ", PetSizes_id = NULL" +
                        ", deletedAt = NOW()" +
                        ", deletedBy = '" + Logged + "' " +
                        "WHERE id = '" + Id + "';";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " +ex);
            throw ex;
        }
    }

    //INSERT a Pet
    public static void insertPet(String Logged, String name, String dateBirth, String Customers_id,
                                 String PetTypes_id, String PetSizes_id)
            throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO pets " +
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
