package messagesystem;

import org.jetbrains.annotations.NotNull;

/**
 * Created by venik on 06.01.17.
 */
public abstract class Message {
    private final String from;
    private final String to;

    public Message(@NotNull String from,@NotNull String to){
        this.from=from;
        this.to=to;
    }

    public String getFrom(){return from;}

    public String getTo() {
        return to;
    }

    public abstract void exec(Abonent abonent);
}
