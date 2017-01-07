package protocol;

/**
 * Created by venik on 07.01.17.
 */
public class CommandAuth extends Command {
    private String user;

    private String token;

    public static final String NAME = "auth";

    public CommandAuth(String user,String token) {
        super(NAME);
        this.user=user;
        this.token=token;
    }

    public String getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public void setUser(String user){this.user=user;}

    public void setToken(String token){this.token=token;}
}
