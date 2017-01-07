import com.sun.istack.internal.NotNull;

/**
 * Created by venik on 07.01.17.
 */
public class Command {
    public static String AUTH = "auth";

    @NotNull
    private final String name;

    public Command( String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
