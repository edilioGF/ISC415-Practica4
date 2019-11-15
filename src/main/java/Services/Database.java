package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database database;

    private  Database(){
        registrarDriver();
    }

    public static Database getInstance() {

        if (database == null){
            database = new Database();
        }
        return database;
    }

    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(String.format("ERROR: %s", e));
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            String DB_URL = "jdbc:h2:tcp://localhost/~/Documents/H2/practica4";
            con = DriverManager.getConnection(DB_URL, "lyriad", "admin");
        } catch (SQLException e) {
            System.out.println(String.format("ERROR: %s", e));
        }
        return con;
    }

    public void testConnection() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            System.out.println(String.format("ERROR: %s", e));
        }
    }
}
