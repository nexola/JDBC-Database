package lesson3;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdatingData {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {

            conn = DB.cDB();

            ps = conn.prepareStatement(
                    "UPDATE seller " // Atualize a tabela seller
                            + "SET BaseSalary = BaseSalary + ? " // Trocando o Salário base por ele mesmo + um valor ainda não especificado
                            + "WHERE " // No local
                            + "(DepartmentId = ?)"); // Coluna DepartmentId, valor ainda não especificado);

            ps.setDouble(1, 200.0);
            ps.setInt(2, 2);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Finalizado! Linhas afetadas: " + rowsAffected);

        } catch (SQLException | RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            DB.closeConnection();
            DB.closeStatement(ps);
        }
    }
}
