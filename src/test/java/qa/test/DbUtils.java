package qa.test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {

    private final Map<String, Object> config;

    public DbUtils(final Map<String, Object> configuration) {
        this.config = configuration;
    }

    public List<Map<String, Object>> queryDB(final String query) {
        final String url = (String) config.get("url");
        final String username = (String) config.get("username");
        final String password = (String) config.get("password");
        final String driverClassName = (String) config.get("driverClassName");
        final String formattedUrl = String.format(url, username, password);

        Connection conn = null;
        Statement stmt = null;

        try {
            final Driver d = (Driver) Class.forName(driverClassName).newInstance();
            DriverManager.registerDriver(new DriverShim(d)); // shim allows loading when class is not on system class loader
            conn = DriverManager.getConnection(formattedUrl);
            System.out.println(String.format("Connection made to MsSql jdbc: %s", formattedUrl));
            stmt = conn.createStatement();
            return resultSetToArrayList(stmt.executeQuery(query));
        } catch (final Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
				//stmt.close;
                conn.close();
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public List<Map<String, Object>> resultSetToArrayList(final ResultSet rs) throws SQLException {
        final ResultSetMetaData md = rs.getMetaData();
        final int columns = md.getColumnCount();
        final List<Map<String, Object>> list = new ArrayList(50);
        while (rs.next()) {
            final Map<String, Object> row = new HashMap(columns);
            for (int i=1; i<=columns; ++i) {           
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        return list;
      }
 
    
}

