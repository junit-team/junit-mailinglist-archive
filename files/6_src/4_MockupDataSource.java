package test.mockups;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.Properties;
import oracle.jdbc.driver.OracleDriver;

public class MockupDataSource implements DataSource {
    
    public MockupDataSource() throws SQLException {
        try {
            Class driverClass = Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("driver oracle.jdbc.driver.OracleDriver not found, " + ex.getMessage());
        }
    }
    
    public Connection getConnection() throws SQLException {
        String url = System.getProperty("user.test.DbUrl","jdbc:oracle:oci8:@ORACLE_SID");
        try {
            Class.forName(oracle.jdbc.driver.OracleDriver.class.getName());
        } catch (ClassNotFoundException ex) {
            throw new SQLException("JDBC driver not found, " + ex.getMessage());
        }
        return DriverManager.getConnection(url, "scott", "tiger");
    }
    
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
