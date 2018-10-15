import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public class DatabaseConnection {
    private static Connection connection = null;

    /** Metodo che ricevuto un URL si connette al DB SQLite*/
    public static void connect(String URL) throws SQLException {
        /* Aggiunta del prefisso corretto per la connessione al DB all'URL */
        URL = "jdbc:sqlite:" + URL;
        connection = DriverManager.getConnection(URL);
    }
}
