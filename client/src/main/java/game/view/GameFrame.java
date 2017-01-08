package game.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by venik on 08.01.17.
 */
public class GameFrame extends JFrame{
    public GameCanvas canvas;
    public static Dimension size = new Dimension(800,600);

    public GameFrame(){
        setSize(size);
        setResizable(false);

        canvas = new GameCanvas();
        add(canvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
