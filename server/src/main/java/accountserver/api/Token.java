package accountserver.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by venik on 05.01.17.
 */
public class Token {
    public Long value;

    @Override
    public String toString(){
        return Long.toString(value);
    }
}
