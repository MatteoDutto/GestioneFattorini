import java.sql.*;

/**
 * Created by inf.francoa3103 on 08/10/2018.
 */
public class SQLiteJDBCDriverConnection {
    private static Connection conn = null;
    public static void connect() {
        try {
            String url = "jdbc:sqlite:C:/Users/inf.francoa3103/Desktop/dbProva.db";
            /* DriverManager -> utilizzato per la gestione di driver JDBC
             * getConnection() -> tenta di collegarsi a ad un db ad un dato url */
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established");

        } catch(SQLException sqle) {
            System.out.printf("Errore: " + sqle.getMessage());
        }
    }

    public static void selectPacco(String codice) {
        String sql = "SELECT * FROM Pacco WHERE Pacco.Codice=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, codice);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Pacco pacco = new Pacco();
                pacco.setCodice(rs.getString("Codice"));
                pacco.setPeso(rs.getDouble("Peso"));
                pacco.setDescrizione(rs.getString("Descrizione"));

                System.out.println(pacco);
            }
        }catch(SQLException sqle) {
            System.out.println("Errore: " + sqle.getMessage());
        };
    }

    public static void insert(Pacco pacco){
        String sql = "INSERT INTO Pacco(Codice,Peso,Descrizione) VALUES(?,?,?)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pacco.getCodice());
            pstmt.setDouble(2, pacco.getPeso());
            pstmt.setString(3, pacco.getDescrizione());
            pstmt.executeUpdate();
        } catch(SQLException sqle){
            System.out.println("Hai fatto un errore: " + sqle.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();
        insert(new Pacco("ABC006", 0.5, "L'amico delle donne Jerry"));
        selectPacco("ABC006");

        try {
            conn.close();
        }catch (SQLException sqle) {
            System.out.println("Errore: " + sqle.getMessage());
        }
    }
}
