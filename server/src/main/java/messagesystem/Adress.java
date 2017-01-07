package messagesystem;

import org.jetbrains.annotations.NotNull;

/**
 * Created by venik on 06.01.17.
 */
public class Adress {
    @NotNull
    private String name;

    public Adress(@NotNull String name){
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return '[' + name + ']';
    }
}
