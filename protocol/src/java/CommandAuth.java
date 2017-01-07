/**
 * Created by venik on 07.01.17.
 */
public class CommandAuth extends Command {
    private final String user;

    private final String token;

    public CommandAuth(String user,String token) {
        super(AUTH);
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
