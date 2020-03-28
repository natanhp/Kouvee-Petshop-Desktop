package main.util;

/* BUG IN THE PACKAGE
Usage of the below package is is not recommended as the result statement
returned for the table's column name are not the column labels, instead using the column names
therefore will cause java.sql.SQLException : invalid column name

Column labels can be used with queries that have aliases whereas
column names referred to the table's original column name
import com.sun.rowset.CachedRowSetImpl;
 */
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import java.sql.*;

public class DBUtil {
    //Declare JDBC MySQL Driver
//    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    //Connection
    private static Connection conn = null;

    //Connection URL
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/9509?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    //Connect to DB
    public static Connection conDB()
    {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            return conn;
        } catch (SQLException ex) {
            System.err.println("ConnectionUtil : "+ex.getMessage());
            return null;
        }
    }

    //Connect to DB
    public static void dbConnect() throws SQLException {
        try {
            conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Connection To The Database Failed ! Check The Console" + ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    //Close Connection to DB
    public static void dbDisconnect() throws SQLException {
        try {
            if(conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    //DB Query Execution Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;

//        CachedRowSetImpl crs = null;
        CachedRowSet crs = null;

        try {
            //Establish MySQL Connection(Connect to DB)
            dbConnect();
            System.out.println("Select Statement: "+ queryStmt + "/n");

            //Create statement
            stmt = conn.createStatement();

            //Execute query operation
            resultSet = stmt.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //Using CachedRowSet preventing "java.sq;.SQLRecoverableException: Closed Connection: next" err

//            crs = new CachedRowSetImpl();
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        }
        catch (SQLException ex) {
            System.out.println("Problem occured at executeQuery operation : " + ex);
            throw ex;
        } finally {
            if(resultSet != null) {
                //Close resultSet
                resultSet.close();
            }

            if (stmt != null) {
                //Close Statement
                stmt.close();
            }

            //Close connection
            dbDisconnect();
        }

        //Return CachedRowSet
        return crs;

    }

    //DB Execute Update (INSERT/UPDATE/DELETE Operation
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;

        try {

            //Establish MySQL Connection(Connect to DB)
            dbConnect();

            //Create Statement
            stmt = conn.createStatement();

            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        }
        catch (SQLException ex){
            System.out.println("Problem occured at executeUpdate operation: " + ex);
            throw ex;
        } finally {
            if(stmt != null) {
                //Close Statement
                stmt.close();
            }

            //Close connection
            dbDisconnect();
        }
    }
}
