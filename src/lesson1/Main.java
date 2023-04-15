package lesson1;

import lesson1.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDB.cDB();

            st = conn.createStatement();

            rs = st.executeQuery("select * from department");

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            ConnectionDB.closeStatement(st);
            ConnectionDB.closeResultSet(rs);
            ConnectionDB.closeConnection();
        }

    }
}
