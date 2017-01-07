import com.sun.istack.internal.NotNull;

/**
 * Created by venik on 07.01.17.
 */
public abstract class Command {

    public static String AUTH = "auth";

    @NotNull
    private final String name;

    public Command(@NotNull String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
