package lesson2;

import lesson1.ConnectionDB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsertingData {

    public static void main(String[] args) throws SQLException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = ConnectionDB.cDB();

            // Inserindo dados no database
            st = conn.prepareStatement(
                    "INSERT INTO seller " // Inserir na tabela seller
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentID)" // Campos chave
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)", // Valores a serem inseridos (se ainda não determinados, inserir '?' para cada um deles)
                            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Carl Purple"); // setTipo(índice do '?' passado no prepareStatement, valor para aquela posição)
            st.setString(2, "purplecarl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // Instanciando um novo objeto Data SQL
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate(); // Retorna a quantia de linhas alteradas no database

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys(); // Retorna um objeto ResultSet com as keys alteradas
                while (rs.next()) { // Enquanto houver mais ResultSets
                    int id = rs.getInt(1); // Recebe o valor da chave no índice do parâmetro
                    System.out.println("Finalizado! Id = " + id);
                }
                System.out.println("Linhas afetadas: " + rowsAffected);
            } else {
                System.out.println("Nenhuma linha alterada!");
            }


        } catch (SQLException | RuntimeException | ParseException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionDB.closeConnection();
            ConnectionDB.closeStatement(st);
        }
    }
}
