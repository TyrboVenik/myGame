package protocol;

/**
 * Created by venik on 07.01.17.
 */
public class Command {

    private String name;

    public Command(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){this.name=name;}
}
