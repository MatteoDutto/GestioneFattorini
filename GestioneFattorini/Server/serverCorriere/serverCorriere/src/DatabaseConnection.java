import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creato da Andrea Delmastro, Alessio Franco.
 * Classe rappresentante una connessione verso il database.
 */
public class DatabaseConnection {
    private static Connection connection = null;

    /** Metodo che ricevuto un URL si connette al DB SQLite
      * @param URL : l'URL del DB al quale collegarsi */
    public static void connect(String URL) throws SQLException {
        /* Aggiunta del prefisso corretto per la connessione al DB all'URL */
        URL = "jdbc:sqlite:" + URL;
        connection = DriverManager.getConnection(URL);
    }

    /** @return l'oggetto rappresentante la connessione */
    public static Connection getConnection() {
        return connection;
    }
}
