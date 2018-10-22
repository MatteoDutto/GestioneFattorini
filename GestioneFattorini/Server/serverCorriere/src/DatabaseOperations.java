/**
 * Created by inf.francoa3103 on 15/10/2018.
 */
public enum DatabaseOperations {
    SELECT_PACK_FROM_ID("SELPACKID"),
    SELECT_DELIVERY_MAN_FROM_ID("SELDELMAN");

    final String string;

    DatabaseOperations(String string) {
        this.string = string;
    }

    public final String getString() {
        return this.string;
    }
}
