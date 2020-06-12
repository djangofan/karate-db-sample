package qa.karate.db;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This shim allows loading of the driver within a context class loader rather than only the system class loader.
 * I believe Karate-Runner VSCode plugin, forks to a context class loader during debug sessions.
 */
public class DriverShim implements Driver {
    
    private Driver driver;

    public DriverShim(Driver d) {
        this.driver = d;
    }

    public boolean acceptsURL(String u) throws SQLException {
        return this.driver.acceptsURL(u);
    }

    public Connection connect(String u, Properties p) throws SQLException {
        return this.driver.connect(u, p);
    }

    public int getMajorVersion() {
        return this.driver.getMajorVersion();
    }

    public int getMinorVersion() {
        return this.driver.getMinorVersion();
    }

    public DriverPropertyInfo[] getPropertyInfo(String u, Properties p) throws SQLException {
        return this.driver.getPropertyInfo(u, p);
    }

    public boolean jdbcCompliant() {
        return this.driver.jdbcCompliant();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this.driver.getParentLogger();
    }

}

