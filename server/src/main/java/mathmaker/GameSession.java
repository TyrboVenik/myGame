package mathmaker;

import model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by venik on 08.01.17.
 */
public class GameSession {

    public static int MAX_PLAYERS_IN_SESSTION = 10;

    @NotNull
    private final List<Player> players;

    public GameSession(){
        players = new CopyOnWriteArrayList<>();
    }

    public void add(Player player){
        players.add(player);
    }

    public boolean hasPlace(){
        if(players.size()<MAX_PLAYERS_IN_SESSTION)
            return true;
        else
            return false;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
