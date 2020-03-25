package main.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Employee;
import main.model.Person;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class PersonDAO {

    //SELECT an Entity
    public static Person searchEntity(String entId, String table) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM " + table + " WHERE Id = " + entId;

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPer = DBUtil.dbExecuteQuery(selectStmt);

            Person person = getPersonFromResultSet(rsPer);

            return person;
        } catch (SQLException ex) {
            System.out.println("While searching " + table + " with Id : " + entId + ", an error occured: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Person getPersonFromResultSet(ResultSet rs) throws SQLException {
        Person per = null;

        if(rs.next()) {
            per = new Person();
            per.setId(rs.getInt("id"));
            per.setName(rs.getString("name"));
            per.setPhoneNumber(rs.getString("phoneNumber"));
            per.setAddress(rs.getString("address"));
        }

        return per;
    }

    //SELECT Persons
public static ObservableList<Person> searchPersons(String table) throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM " + table +";";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPers = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Person> perList = getPersonList(rsPers);

            //Return Person Object
            return perList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM table operation
    public static ObservableList<Person> getPersonList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Person Objects
        ObservableList<Person> perList = FXCollections.observableArrayList();

        while(rs.next()) {
            Person per = new Person();
            per = new Person();
            per.setId(rs.getInt("id"));
            per.setName(rs.getString("name"));
            per.setPhoneNumber(rs.getString("phoneNumber"));
            per.setAddress(rs.getString("address"));

            //Add employee to the ObservableList
            perList.add(per);
        }

        //Return empList (ObservableList of Employees
        return perList;
    }

    //Update an entities entries
    public abstract void updateEntries (String Id, String name, String address,
                                      String phoneNumber, String table)
            throws SQLException, ClassNotFoundException;

    //DELETE an entity
    public static void deleteEntityWithId(String Id, String table) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM " + table +
                        " WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //INSERT an Entity
    public abstract void insertEmp(String name, String address,
                                 String phoneNumber)
            throws SQLException, ClassNotFoundException;
}
