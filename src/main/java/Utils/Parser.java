package Utils;

import Services.Database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Parser {

    public static String generateUUID(String table) {

        String uuid = null;
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
            String query;
            ResultSet dbResult;

            do {

                uuid = UUID.randomUUID().toString();
                query = String.format("SELECT id FROM %s WHERE id = '%s'", table, uuid);
                dbResult = conn.prepareStatement(query).executeQuery();
                dbResult.last();

            } while (dbResult.getRow() != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return uuid;
    }

    public static String getHashedPassword (String password) {
        String hashPassword = null;
        MessageDigest hasher = null;
        try {
            hasher = MessageDigest.getInstance("SHA-512");
            hasher.reset();
            hasher.update(password.getBytes("UTF-8"));
            hashPassword = String.format("%0128x", new BigInteger(1, hasher.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashPassword;
    }

    public static boolean comparePassword (String password, String hashToCompare) {

        String hashPassword = getHashedPassword(password);

        return hashPassword.equals(hashToCompare);
    }
}
