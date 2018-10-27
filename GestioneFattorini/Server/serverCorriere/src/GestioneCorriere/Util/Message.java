package GestioneCorriere.Util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creato da Andrea Delmastro <br><br>
 * Oggetto rappresentante un messaggio.
 * Il messaggio è l'elemento chiave di una comunicazione client-server-database.
 * Il sistema di funzionamento è abbastanza semplice: il client invia un messaggio
 * al server dove ha parametrizzato ciò che gli serve, il server risponde inviando
 * indietro <b>lo stesso messaggio, se il messaggio produce risultato</b> con eventualmente parametrizzata la
 * componente risultato. Sarebbe buona norma non manipolare la componente risultato prima
 * che sia il server a farlo, altrimenti ciò che il client ha aggiunto al risultato potrebbe
 * corrompere il risultato stesso. In parole povere: il client tocca i campi che servono
 * al client, il server tocca i campi che servono al server. Modificare resPacks prima dell'invio
 * al server del messaggio è logicamente insensato e deleterio.<br><br>
 * Un messaggio si compone di una serire di campi: <br><br>
 * 1) <i>DatabaseOperation</i> operation: è la stringa rappresentante l'operazione che si
 *   vuol fare effettuare al server sul database. Le operazioni sono standard e
 *   raggruppate nell'enumerazione DatabaseOperations. <b>Utilizzare quelle stringhe.</b><br><br>
 * 2) <i>Pack</i> packOptions: è il pacco che rappresenta le opzioni di ricerca sul database.
 *   <b>Non deve necessariamente essere utilizzato</b>. Viene utilizzato quando l'operazione
 *   richiede dei parametri.<br><br>Esempi:<br>
 *   - se voglio ottenere un pacco in base all'ID imposterò la DatabaseOperation corrispondente
 *   e imposterò un pacco in packOptions che abbia come
 *   proprietà settata l'ID. <b>Il server, in questo caso, controllerà solo l'ID, le altre
 *   proprietà di packOptions possono anche non essere impostate.</b><br>
 *   - se volgio aggiungere un pacco al database imposterò la DatabaseOperation corrispondente
 *   e imposterò il pacco che voglio aggiungere in packOptions. <b> Tutte le proprietà del pacco
 *   devono essere valorizzate.</b><br><br>
 * 3) <i>ArrayList</i> resPacks: è il risultato della query inerente ai pacchi. Se la query ha prodotto un solo risultato
 *    sarà comunque valorizzato con dentro <b>un solo pacco</b>. Se la query ha prodotto <i>n</i> risultati
 *    qui dentro saranno contenuti tutti gli <i>n</i> pacchi della query.
 */

public class Message implements Serializable {
    static final long serialVersionUID = 42L;
    private DatabaseOperations operation = DatabaseOperations.VOID_OPERATION;
    private Pack packOptions = null;
    private ArrayList<Pack> resPacks = null;

    public Message() {}

    public Message(DatabaseOperations operation) {
        this.operation = operation;
        this.resPacks = new ArrayList<Pack>();
    }

    public Message(DatabaseOperations operation, Pack packOptions) {
        this.operation = operation;
        this.packOptions = packOptions;
        this.resPacks = new ArrayList<Pack>();
    }

    public Message(DatabaseOperations operation, ArrayList<Pack> resPacks, Pack packOptions) {
        this.operation = operation;
        this.resPacks = resPacks;
        this.packOptions = packOptions;
    }

    public Pack getOptions() {
        return packOptions;
    }

    public void setOptions(Pack packOptions) {
        this.packOptions = packOptions;
    }

    public void addSinglePack(Pack pack) {
        resPacks.add(pack);
    }

    public Pack getSinglePack () {
        return resPacks.get(0);
    }

    public DatabaseOperations getOperation() {
        return operation;
    }

    public void setOperation(DatabaseOperations operation) {
        this.operation = operation;
    }

    public ArrayList<Pack> getPacks() {
        return resPacks;
    }

    public void setPacks(ArrayList<Pack> resPacks) {
        this.resPacks = resPacks;
    }
}
