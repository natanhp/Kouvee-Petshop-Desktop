package main.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.Employee;
import main.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class EmployeeDAO {

    //SELECT an Employee
    public static ObservableList<Employee> searchEmployee(String empName) throws SQLException {

        //Declare a SELECT Statement
        String selectStmt = "SELECT * FROM Employees WHERE name LIKE '%" + empName + "%' AND deletedAt IS NULL;";

        //Execute SELECT Statement
        try {

            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

            return getEmployeeList(rsEmp);
        } catch (SQLException ex) {
            System.out.println("While searching an employee with Name : " + empName + ", an error occurred: " + ex);
            //Return Exception
            throw ex;
        }
    }

    //Search an existing Employee with Username
    public static Employee searchUsername(String empUname) throws SQLException {

        //Declare a specific SELECT statement
        String queryCheck = "SELECT id, name, role, password FROM Employees WHERE username = '" + empUname + "';";

        //Execute SELECT the Statement
        try {
            //Get ResultSet from DbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(queryCheck);

            return getEmployeeFromResultSet(rsEmp);
        } catch (SQLException ex) {
            System.out.println("While checking for existing employee with a username : " + empUname + ", an error occurred " + ex);
            //Return Exception
            throw ex;
        }
    }

    private static Employee getEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee emp = null;

        if (rs.next()) {
            emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setDateBirth(rs.getDate("dateBirth"));
            emp.setPhoneNumber(rs.getString("phoneNumber"));
            emp.setAddress(rs.getString("address"));
            emp.setRole(rs.getString("role"));
            emp.setUsername(rs.getString("username"));
            emp.setPassword(rs.getString("password"));
        }

        return emp;
    }

    //SELECT Employees
    public static ObservableList<Employee> searchEmployees() throws SQLException {

        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM Employees WHERE deletedAt IS NULL";

        //Execute SELECT Statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getEmployeeList method and get employee object

            //Return Employee Object
            return getEmployeeList(rsEmps);
        } catch (SQLException ex) {
            System.out.println("SQL Select Operation has been failed: " + ex);

            //Return exception
            throw ex;
        }
    }

    //SELECT * FROM employees operation
    public static ObservableList<Employee> getEmployeeList(ResultSet rs) throws SQLException {

        //Declare a observable List which comprises of Employee Objects
        ObservableList<Employee> empList = FXCollections.observableArrayList();

        while (rs.next()) {
            Employee emp;
            emp = new Employee();
            emp.setId(rs.getInt("id"));
            emp.setName(rs.getString("name"));
            emp.setDateBirth(rs.getDate("dateBirth"));
            emp.setPhoneNumber(rs.getString("phoneNumber"));
            emp.setAddress(rs.getString("address"));
            emp.setRole(rs.getString("role"));
            emp.setUsername(rs.getString("username"));
            emp.setPassword(rs.getString("password"));

            //Add employee to the ObservableList
            empList.add(emp);
        }

        //Return empList (ObservableList of Employees
        return empList;
    }

    //Update an employee's entries
    public static void updateEntries(String Logged, String Id, String name, String dateBirth, String address,
                                     String phoneNumber, String role, String username, String password)
            throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt;
        //Update statement will be checked if new password is entered
        System.out.println(password);
        if (!password.equals("No change")) {
            updateStmt =
                    "UPDATE Employees " +
                            "SET name = '" + name + "' " +
                            ", address = '" + address + "' " +
                            ", dateBirth = '" + dateBirth + "' " +
                            ", phoneNumber = '" + phoneNumber + "' " +
                            ", role = '" + role + "' " +
                            ", updatedAt = NOW()" +
                            ", updatedBy = '" + Logged + "' " +
                            ", username = '" + username + "' " +
                            ", password = '" + password + "' " +
                            "WHERE id = '" + Id + "' AND deletedAt IS NULL;";
        } else {
            updateStmt =
                    "UPDATE Employees " +
                            "SET name = '" + name + "' " +
                            ", address = '" + address + "' " +
                            ", dateBirth = '" + dateBirth + "' " +
                            ", phoneNumber = '" + phoneNumber + "' " +
                            ", role = '" + role + "' " +
                            ", updatedAt = NOW()" +
                            ", updatedBy = '" + Logged + "' " +
                            ", username = '" + username + "' " +
                            "WHERE id = '" + Id + "' AND deletedAt IS NULL;";
        }

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //Update an employee's entries
    public static void updateSpecificEntries(String Logged, String Id, String name, String dateBirth, String address,
                                             String phoneNumber, String role, String username)
            throws SQLException {
        //Declare an UPDATE Statement
        String updateStmt =
                "UPDATE Employees " +
                        "SET name = '" + name + "' " +
                        ", address = '" + address + "' " +
                        ", dateBirth = '" + dateBirth + "' " +
                        ", phoneNumber = '" + phoneNumber + "' " +
                        ", role = '" + role + "' " +
                        ", updatedAt = NOW()" +
                        ", updatedBy = '" + Logged + "' " +
                        ", username = '" + username + "' " +
                        "WHERE id = '" + Id + "';";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while UPDATE Operation:" + ex);
            throw ex;
        }
    }

    //DELETE an employee
    public static void deleteEmpWithId(String Id) throws SQLException {

        //Declare a DELETE Statement
        String updateStmt =
                "DELETE FROM Employees " +
                        "WHERE id = " + Id + ";";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while DELETE Operation: " + ex);
            throw ex;
        }
    }

    //SOFT DELETE an employee
    public static void softDeleteEmpWithId(String Logged, String Id) throws SQLException {

        //Declare an UPDATE Statement
        String deleteStmt =
                "UPDATE Employees " +
                        "SET " +
                        " deletedAt = NOW()" +
                        ", deletedBy = " + Logged +
                        " WHERE id = " + Id + " AND deletedAt IS NULL;";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while SOFT_DELETE Operation: " + ex);
            throw ex;
        }
    }

    //INSERT an Employee
    public static void insertEmp(String Logged, String name, String dateBirth, String address,
                                 String phoneNumber, String role, String username, String password)
            throws SQLException {

        //Declare an INSERT Statement
        String updateStmt =
                "INSERT INTO Employees " +
                        "(name, address, dateBirth, phoneNumber, createdAt, role, createdBy, username, password)" +
                        "VALUES " +
                        "('" + name + "','" + address + "','" + dateBirth + "','" + phoneNumber +
                        "', NOW()," +
                        "'" + role + "','" + Logged + "','" + username + "','" + password + "')";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException ex) {

            System.out.println("Error occurred while INSERT operation: " + ex);
            throw ex;
        }
    }

    public Employee login(String username, String password) {
        String queryCheck = "SELECT id, name, role, password FROM Employees WHERE username = ? AND deletedAt IS NULL;";
        try (Connection connection = DBUtil.conDB()) {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(queryCheck);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                String temp;
                String tempPassword;
                do {
                    temp = resultSet.getString("password");
                    tempPassword = temp;
                } while (resultSet.next());

                BCrypt.Result matched = BCrypt.verifyer().verify(password.toCharArray(), tempPassword);
                if (matched.verified) {
                    resultSet.first();
                    Employee emp = new Employee();
                    do {
                        emp.setId(resultSet.getInt("id"));
                        emp.setName(resultSet.getString("name"));
                        emp.setRole(resultSet.getString("role"));
                    } while (resultSet.next());

                    return emp;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
