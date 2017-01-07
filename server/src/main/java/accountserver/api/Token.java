package accountserver.api;


/**
 * Created by venik on 05.01.17.
 */
public class Token {
    public long value;

    @Override
    public String toString(){
        return Long.toString(value);
    }

    @Override
    public boolean equals(Object obj){
        try {
            Token token = (Token) obj;
            if (value == token.value)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }
}
