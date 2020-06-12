package qa.karate.db;

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



public class MSSqlUtil {

    private final Map<String, Object> config;

    public MSSqlUtil(final Map<String, Object> configuration) {
        this.config = configuration;
    }

    public List<Map<String, Object>> queryDB(final String query) {
        final String formattedUrl = String.format((String)config.get("url"), (String)config.get("username"), (String)config.get("password"));
        loadDriverOnClasspath();

        try (Connection conn = DriverManager.getConnection(formattedUrl);
             Statement stmt = conn.createStatement();
        ) {
            System.out.println(String.format("Connection made to MsSql jdbc: %s", formattedUrl));
            return resultSetToArrayList(stmt.executeQuery(query));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Map<String, Object>> resultSetToArrayList(final ResultSet rs) throws SQLException {
        final ResultSetMetaData md = rs.getMetaData();
        final int columns = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }

    private void loadDriverOnClasspath() {
        String driverClassName = (String) config.get("driverClassName");
        try {
            Driver driver = (Driver) Class.forName(driverClassName).newInstance();
            // shim is required for debugging with Karate
            DriverManager.registerDriver(new DriverShim(driver));
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

