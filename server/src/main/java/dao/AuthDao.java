package dao;

import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by venik on 05.01.17.
 */
public class AuthDao {

    private static final Logger log = LogManager.getLogger(AuthDao.class);

    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS MyGame" +
            "(" +
            " ID serial PRIMARY KEY NOT NULL," +
            "  \"user\" character varying(10)," +
            "  password character varying(10)" +
                    ")";

    private static final String INSERT =
            "INSERT INTO MyGame (\"user\",password) VALUES ('%s','%s')";

    private static final String GET =
            "SELECT * FROM MyGame WHERE \"user\"='%s';";

    private void createTable(Connection con)throws SQLException{
        Statement stm = con.createStatement();
        stm.execute(CREATE_TABLE);
    }

    public void insert(Pair<String,String> pair){
        try(Connection con = DbConnector.getConnection()){
            createTable(con);
            Statement stm = con.createStatement();
            stm.execute(String.format(INSERT,pair.getKey(),pair.getValue()));
        }catch (SQLException e) {
            log.error("Failed to add record {}", e);
        }
    }

    public Pair<String,String> get(String name){
        try(Connection con = DbConnector.getConnection()){
            Pair<String,String> answer = null;

            Statement stm = con.createStatement();
            ResultSet res  = stm.executeQuery(String.format(GET,name));

            if(res.next()){
                answer = new Pair<>(res.getString("user"),res.getString("password"));
            }
            return answer;
        }catch (SQLException e) {
            log.error("Failed to get {}", e);
            return null;
        }
    }

    public static void main(@NotNull String[] args){
        AuthDao dao = new AuthDao();
        dao.insert(new Pair<>("log3","pass1"));
        System.out.println(dao.get("log3"));
    }

}
