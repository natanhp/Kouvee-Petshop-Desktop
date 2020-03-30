package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Service;
import main.model.Service;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDAO {

    //SELECT a Service
    public static Service searchService(String sId) throws SQLException, ClassNotFoundException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM  services WHERE Id =" + sId;

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsS = DBUtil.dbExecuteQuery(selectStmt);

            Service service = getServicesFromResultSet(rsS);

            return service;
        } catch (SQLException ex) {
            System.out.println("While searching a service with Id : " + sId + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Service getServicesFromResultSet(ResultSet rs) throws SQLException {
        Service s = null;

        if(rs.next()) {
            s = new Service();
            s.setId(rs.getInt("id"));
            s.setServiceName(rs.getString("serviceName"));
        }

        return s;
    }

    //SELECT Services
    public static ObservableList<Service> searchServices() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM services";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsS = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getServiceList method and get service object
            ObservableList<Service> sList = getServiceList(rsS);

            //Return Service Object
            return sList;
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex ;
        }
    }

    //SELECT * FROM services operation
    public static ObservableList<Service> getServiceList(ResultSet rs) throws SQLException, ClassNotFoundException {

        //Declare a observable List which comprises of Service Objects
        ObservableList<Service> sList = FXCollections.observableArrayList();

        while(rs.next()) {
            Service s = new Service();
            s = new Service();
            s.setId(rs.getInt("id"));
            s.setServiceName(rs.getString("serviceName"));

            //Add service to the ObservableList
            sList.add(s);
        }

        //Return sList (ObservableList of Services
        return sList;
    }

    //Update a service's entries
    public static void updateEntries (String Logged, String Id, String serviceName) throws SQLException, ClassNotFoundException
    {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE services " +
                        "SET serviceName = '" + serviceName + "' " +
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

    //DELETE a service
    public static void deleteSWithId(String Id) throws SQLException, ClassNotFoundException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM services " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " +ex);
            throw ex;
        }
    }

    //SOFT DELETE a service
    public static void softDeleteSWithId(String Logged, String Id) throws SQLException, ClassNotFoundException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE services " +
                        "SET serviceName = NULL" +
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

    //INSERT a Service
    public static void insertS(String Logged, String serviceName) throws SQLException, ClassNotFoundException
    {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO services " +
                        "(serviceName, createdAt, createdBy)" +
                        "VALUES " +
                        "('" + serviceName + "', " + "NOW(), " +
                        "'" + Logged + "');";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }
}
