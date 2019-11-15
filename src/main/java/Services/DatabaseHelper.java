package Services;

import Handlers.UserHandler;
import Models.User;
import Utils.Parser;
import org.h2.jdbc.JdbcSQLException;
import org.h2.tools.Server;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    public static void startDatabase() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    public static void stopDatabase() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }

    public static void createAdmin() throws SQLException {
        User user = new User("","admin", "Admin", Parser.getHashedPassword("123456"), true,true);
        UserHandler.getInstance().crear(user);
    }
}
