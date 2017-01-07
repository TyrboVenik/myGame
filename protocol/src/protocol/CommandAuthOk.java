package protocol;

/**
 * Created by venik on 08.01.17.
 */
public class CommandAuthOk extends Command {
    public static final String NAME = "auth_ok";

    private int id;

    public CommandAuthOk(int id) {
        super(NAME);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
