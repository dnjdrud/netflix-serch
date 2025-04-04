package main.com.dnjdrud.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConfig {
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/netflix?useSSL=false&allowPublicKeyRetrieval=true");
        config.setUsername("root");
        config.setPassword("Ww32162761!");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
