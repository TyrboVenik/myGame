package protocol;

/**
 * Created by venik on 08.01.17.
 */
public class CommandAuthFail extends Command  {
    public static final String NAME = "auth_fail";

    private String cause;

    public CommandAuthFail( String cause) {
        super(NAME);
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }
}
