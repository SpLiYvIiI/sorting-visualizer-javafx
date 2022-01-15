package racxa.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Slf4j
public class JdbcConnection {
    public static Connection getConnection() throws SQLException {
        String dbUrl = Constants.jdbcConnectionUrl;
        String dbUser = Constants.jdbcUserName;
        String dbPass = Constants.jdbcPassword;
        Connection c = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        return c;
    }
}
