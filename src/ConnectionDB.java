import java.sql.*;

public class ConnectionDB {

    private static Connection conn = null;

    public static Connection cDB()  {

        try {
            if (conn == null) {
                String url = "jdbc:mysql://localhost:3306/coursejdbc?user=developer&password=1234&useSSL=false";
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
