import com.sun.istack.internal.NotNull;

/**
 * Created by venik on 07.01.17.
 */
public class CommandAuth extends Command {

    private final String user;

    private final String token;

    public CommandAuth(@NotNull String user, @NotNull String token) {
        super(Command.AUTH);
        this.user=user;
        this.token=token;
    }

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }
}
