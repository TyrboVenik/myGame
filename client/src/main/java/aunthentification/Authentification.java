package aunthentification;

import com.squareup.okhttp.*;
import com.sun.istack.internal.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

/**
 * Created by venik on 05.01.17.
 */
public class Authentification {
    @NotNull
    private static final Logger log = LogManager.getLogger(Authentification.class);
    @NotNull
    private static String servoceUrl = "http://" + "localhost"+":"+7000;
    @NotNull
    private final OkHttpClient client = new OkHttpClient();

    public boolean register(@NotNull String user,@NotNull String password){
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(
          mediaType,
                String.format("user=%s&password=%s",user,password)
        );

        String requestUrl = servoceUrl + "/auth/register";

        Request request = new Request.Builder().
                url(requestUrl).
                post(body).
                addHeader("content-type", "application/x-www-form-urlencoded").
                build();

        try {
            Response response = client.newCall(request).execute();
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String login(@NotNull String user,@NotNull String password){
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(
                mediaType,
                String.format("user=%s&password=%s",user,password)
        );

        String requestUrl = servoceUrl + "/auth/login";

        Request request = new Request.Builder().
                url(requestUrl).
                post(body).
                addHeader("content-type", "application/x-www-form-urlencoded").
                build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }else
                return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(@NotNull String[] args){
        Authentification authentification = new Authentification();
        System.out.println(authentification.login("user","password"));
    }
}
