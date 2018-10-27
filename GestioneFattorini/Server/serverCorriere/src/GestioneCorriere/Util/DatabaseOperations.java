package GestioneCorriere.Util;

/**
 * Creato da Andrea Delmastro, Alessio Franco <br><br>
 * Classe enumerazione che contiene tutte le operazioni standard
 * che possono essere effettuate sopra al database. <b>Le uniche operazioni
 * consentite e riconosciute dal server sono quelle codificate in questa
 * enumerazione.</b><br><br>
 * Ogni DatabaseOperation è di fatto una stringa. L'utilizzo di una enumerazione
 * rende più facile e veloce il confronto fra operazioni, l'aggiunta, la modifica, la
 * rimozione di un'operazione.
 */
public enum DatabaseOperations {
    SELECT_PACK_FROM_ID("SELPACKID"),
    SELECT_ALL_PACKS("SELALLPACKS"),
    SELECT_ALL_NON_SENT_PACKS("SELALLNSPACKS"),
    INSERT_PACK("INSPACK"),
    REMOVE_PACK_FROM_ID("REMPACKID"),
    VOID_OPERATION("");

    final String string;

    /** Metodo costruttore che istanzia una DatabaseOperation.
      * Il metodo <b>non deve essere utilizzato per creare nuove DatabaseOperations
      * da codice</b>: queste operazioni <b>non</b> verrebbero riconosciute dal sistema
      * e genererebbero una chiusura del socket da parte del server senza che operazione
      * alcuna venga effettuata. Le uniche Database operations ammesse sono quelle già
      * codificate in questa classe */
    DatabaseOperations(String string) {
        this.string = string;
    }

    /** Metodo che ritorna la stringa rappresentante la DatabaseOperation. */
    public final String getString() {
        return this.string;
    }
}
