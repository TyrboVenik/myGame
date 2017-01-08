package mathmaker;

import main.MasterServer;
import model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;
/**
 * Created by venik on 08.01.17.
 */
public class MatchMaker {
    private final static Logger log = LogManager.getLogger(MatchMaker.class);
    private List<GameSession> gamesessions = new CopyOnWriteArrayList<>();

    public void joinGame(Player player){
       selectGameSession().add(player);
        log.info("Player {} with id {} join the game", player.getName(),player.getId());
    }

    private GameSession selectGameSession(){
        for (GameSession gameSession:gamesessions){
            if(gameSession.hasPlace()) {
                return gameSession;
            }
        }

        GameSession gameSession = new GameSession();
        gamesessions.add(gameSession);
        return gameSession;
    }

    public void leave(Player player) {
        for (GameSession gamesession: gamesessions){
            List<Player> playerList = gamesession.getPlayers();
            if(playerList.contains(player)) {
                playerList.remove(player);
                if(playerList.size()==0)
                    gamesessions.remove(gamesession);
            }
        }
    }

}
