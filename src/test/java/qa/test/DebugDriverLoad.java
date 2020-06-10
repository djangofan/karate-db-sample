package qa.test;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Objects;

import qa.test.DriverShim;

public class DebugDriverLoad {

        @org.junit.Test
        public void will_not_work() {
            try {
                URL u = new URL("jar:file:libs/mssql-jdbc-8.2.2.jre8.jar!/");
                String classname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                URLClassLoader ucl = new URLClassLoader(new URL[]{u});
                Class.forName(classname, true, ucl);
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST", "sa", "mssql123");
                // failure
                Objects.nonNull(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @org.junit.Test
        public void will_work() {
            try {
            URL u = new URL("jar:file:libs/mssql-jdbc-8.2.2.jre8.jar!/");
            String classname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            URLClassLoader ucl = new URLClassLoader(new URL[] { u });
            Driver d = (Driver)Class.forName(classname, true, ucl).newInstance();
            DriverManager.registerDriver(new DriverShim(d));
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TEST", "sa", "mssql123");
            Objects.nonNull(conn);
            // Success!
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}

