package game;

import game.view.GameFrame;

/**
 * Created by venik on 08.01.17.
 */
public class Game extends Thread {
    private GameFrame frame;

    public Game(){
        frame = new GameFrame();
    }

    @Override
    public void run(){
       while (!currentThread().isInterrupted()){
           updateGame();
       }
    }

    private void updateGame(){
        frame.canvas.render();
    }
}
