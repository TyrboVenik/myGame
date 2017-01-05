package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by venik on 05.01.17.
 */
public class DbConnector {
    private static final Logger log = LogManager.getLogger(DbConnector.class);

    private static final String URL = "jdbs:postgresql://localhost:5432/test_db";

    static {
        log.info("Connectiong data base...");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error(e);
            System.exit(-1);
        }
        log.info("Success. DbConnector init.");
    }

    static Connection getConnection()throws SQLException{
        return DriverManager.getConnection(URL,"test","test");
    }

    private DbConnector(){}
}
