/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public enum DatabaseOperations {
    SELECT_PACCO_FROM_ID("SELPACKID:");

    final String string;

    DatabaseOperations(String string) {
        this.string = string;
    }

    public final String getString() {
        return this.string;
    }
}
