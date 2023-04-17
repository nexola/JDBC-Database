package lesson5;

import db.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.cDB();

            conn.setAutoCommit(false); // Não confirma as operações automaticamente, as mesmas ficam pendentes de uma confirmação posterior

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller " +
                    "SET BaseSalary = 2090 " +
                    "WHERE DepartmentId = 1");

            int rows2 = st.executeUpdate("UPDATE seller " +
                    "SET BaseSalary = 3090 " +
                    "WHERE DepartmentId = 2");

            conn.commit(); // Confirma a pendência do AutoCOmmit

            System.out.println("rows 1 = " + rows1);
            System.out.println("rows 2 = " + rows2);


        } catch (SQLException e) {
            try {
                conn.rollback(); // Volta ao estado inicial do MySQL Database
                System.out.println("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                System.out.println("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        } finally {
            DB.closeConnection();
            DB.closeStatement(st);
        }

    }
}
