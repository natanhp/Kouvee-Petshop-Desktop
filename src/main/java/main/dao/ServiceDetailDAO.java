package main.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.ServiceDetail;
import main.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceDetailDAO {

    public static ObservableList<ServiceDetail> getServiceDetails() throws SQLException, ClassNotFoundException {

        //Declare a SELECT statement
        String selectStmt = "SELECT sd.id AS id, " +
                "CONCAT(s.serviceName, ' ', pt.type, ' ', ps.size) AS completeName, " +
                "sd.price AS price, sd.Services_id AS serviceId, sd.PetTypes_id AS petTypeId, " +
                "sd.PetSizes_id AS petSizeId  FROM ServiceDetails sd " +
                "JOIN Services s ON sd.Services_id = s.id " +
                "JOIN PetTypes pt ON sd.PetTypes_id = pt.id " +
                "JOIN PetSizes ps ON sd.PetSizes_id = ps.id " +
                "WHERE (sd.deletedAt IS NULL AND s.deletedAt IS NULL AND pt.deletedAt IS NULL AND ps.deletedAt IS NULL);";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet resultSet = DBUtil.dbExecuteQuery(selectStmt);
            resultSet.getMetaData().getColumnName(1);
            resultSet.getMetaData().getColumnName(2);

            resultSet.getMetaData();

            //Send ResultSet to the getPetList method and get pet object

            //Return Pet Object
            return getServiceDetailList(resultSet);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    public static ObservableList<ServiceDetail> getServiceDetailList(ResultSet rs) throws SQLException {
        ObservableList<ServiceDetail> serviceDetails = FXCollections.observableArrayList();

        while (rs.next()) {
            ServiceDetail serviceDetail = new ServiceDetail();
            serviceDetail.setId(rs.getInt("id"));
            serviceDetail.setCompleteName(rs.getString("completeName"));
            serviceDetail.setPrice(rs.getDouble("price"));
            serviceDetail.setServiceId(rs.getInt("serviceId"));
            serviceDetail.setPetTypeId(rs.getInt("petTypeId"));
            serviceDetail.setPetSizeId(rs.getInt("petSizeId"));
            serviceDetails.add(serviceDetail);

        }

        return serviceDetails;
    }

    public static void updateServiceDetail(String employeeId, ServiceDetail serviceDetail)
            throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt = "UPDATE ServiceDetails SET " +
                "price = " + serviceDetail.getPrice() + ", " +
                "updatedAt = NOW(), " +
                "PetTypes_id = " + serviceDetail.getPetTypeId() + ", " +
                "PetSizes_id = " + serviceDetail.getPetSizeId() + ", " +
                "Services_id = " + serviceDetail.getServiceId() + ", " +
                "updatedBy = " + employeeId +
                " WHERE id = " + serviceDetail.getId() + " AND deletedAt IS NULL;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    public static void softDeletePetWithId(String Id) throws SQLException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE ServiceDetails " +
                        "SET " +
                        "deletedAt = NOW()" +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " + ex);
            throw ex;
        }
    }

    public static void insertServiceDetail(String employeeId, ServiceDetail serviceDetail) throws SQLException {
        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO ServiceDetails " +
                        "(price, createdAt, PetTypes_id, PetSizes_id, Services_id, createdBy)" +
                        "VALUES (" + serviceDetail.getPrice() + ", NOW(), " + serviceDetail.getPetTypeId() + ", " + serviceDetail.getPetSizeId() +
                        ", " + serviceDetail.getServiceId() + ", " + employeeId + ");";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }

}
