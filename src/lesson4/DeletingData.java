package lesson4;

import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletingData {
    public static void main(String[] args) {

        Connection conn;
        PreparedStatement ps = null;

        try {

            conn = DB.cDB();

            ps = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE "
                    + "Id = ?");

            ps.setInt(1, 2);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Finalizado! Linhas afetadas: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeConnection();
            DB.closeStatement(ps);
        }
    }
}
